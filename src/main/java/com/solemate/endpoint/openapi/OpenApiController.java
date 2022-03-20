package com.solemate.endpoint.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.solemate.endpoint.openapi.service.AirQualityService;
import com.solemate.endpoint.openapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OpenApiController {
    private final AirQualityService airQualityService;
    private final WeatherService weatherService;

    @GetMapping("openApi/{currLat}/{currLon}")
    public ResponseEntity<String> getAirQuality(@PathVariable double currLat, @PathVariable double currLon) throws ParseException, JsonProcessingException {
       return airQualityService.getAirQuality(currLat, currLon);
    }

    @GetMapping("openApi/weather/{currLat}/{currLon}")
    public ResponseEntity<String> getWeather(@PathVariable double currLat, @PathVariable double currLon) throws ParseException, JsonProcessingException {
        return weatherService.getWeather(currLat, currLon);
    }
}
