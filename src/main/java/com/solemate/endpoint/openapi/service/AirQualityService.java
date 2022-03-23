package com.solemate.endpoint.openapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solemate.config.ApiKey;
import com.solemate.endpoint.openapi.dto.StationDto;
import com.solemate.endpoint.openapi.dto.StationItemsDto;
import lombok.RequiredArgsConstructor;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AirQualityService {
    private final RestTemplate restTemplate;
    private final ApiKey apiKey;

    public ResponseEntity<String> getAirQuality(double currLat, double currLon) throws ParseException, JsonProcessingException {
        String stationName = getNearStationName(currLat, currLon, jsonParsing(getStationList()));
        System.out.println(stationName);
        URI uri = getAirQualityUri(stationName);
        return restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<String> getStationList() {
        URI uri = getStationListUrl();
        return restTemplate.getForEntity(uri, String.class);
    }

    private List<StationItemsDto> jsonParsing(ResponseEntity<String> response) throws ParseException, JsonProcessingException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(Objects.requireNonNull(response.getBody()));
        JSONObject responseObject = (JSONObject) jsonObject.get("response");
        String bodyObject =  responseObject.get("body").toString();
        ObjectMapper mapper = new ObjectMapper();
        StationDto dtos = mapper.readValue(bodyObject, StationDto.class);
        return dtos.getItems();
    }


    private URI getStationListUrl(){
        String key = apiKey.getStationKey();

        return  UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getMsrstnList")
                .queryParam("serviceKey", key)
                .queryParam("returnType","json")
                .queryParam("numOfRows","700")
                .build(true)
                .toUri();
    }

    private URI getAirQualityUri(String stationName){
        String key = apiKey.getAirQualityKey();
        String encodedStationName = UriEncoder.encode(stationName);

        return UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty")
                .queryParam("serviceKey", key)
                .queryParam("returnType","json")
                .queryParam("numOfRows","1")
                .queryParam("stationName", encodedStationName)
                .queryParam("dataTerm", "DAILY")
                .queryParam("ver","1.0")
                .build(true)
                .encode(StandardCharsets.UTF_8)
                .toUri();
    }


    private String getNearStationName(double currLat, double currLon, List<StationItemsDto> stationItemsDto) {
        String StationName = "";
        double nearDist = 10000;

        for(StationItemsDto dto : stationItemsDto){
            double theta = currLon - dto.getDmY();
            double dist = Math.sin(deg2rad(currLat)) * Math.sin(deg2rad(dto.getDmX())) + Math.cos(deg2rad(currLat)) * Math.cos(deg2rad(dto.getDmX())) * Math.cos(deg2rad(theta));

            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;

            if (dist < nearDist) {
                nearDist = dist;
                StationName =dto.getStationName();
            }
        }
        return StationName;
    }

     //This function converts decimal degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}


