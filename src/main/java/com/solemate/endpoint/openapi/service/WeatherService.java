package com.solemate.endpoint.openapi.service;

import com.solemate.config.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private final ApiKey apiKey;
    private final RestTemplate restTemplate;

    public ResponseEntity<String> getWeather(double currLat, double currLon){
        LatXLngY latXLngY = convertGrid(currLat, currLon);
        URI uri = getUri(latXLngY.x, latXLngY.y);
        return restTemplate.getForEntity(uri, String.class);
    }


    private String getBaseDate(){
        LocalDate now = LocalDate.now(); // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd"); // 포맷 적용
        return now.format(formatter);
    }

    private String getBaseTime(){
        LocalTime now = LocalTime.now();
        LocalTime baseTime = now.minusMinutes(40);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm"); // 포맷 적용
        return baseTime.format(formatter);
    }

    private URI getUri(int x, int y ){
        String key = apiKey.getWhetherKey();

        return UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst")
                .queryParam("serviceKey", key)
                .queryParam("numOfRows","40")
                .queryParam("dataType","JSON")
                .queryParam("base_date", getBaseDate())
                .queryParam("base_time",getBaseTime())
                .queryParam("ny", y)
                .queryParam("nx",x)
                .build(true)
                .encode(StandardCharsets.UTF_8)
                .toUri();
    }

    // 위경도 -> 기상청 좌표 변환 함수
    private LatXLngY convertGrid(double currLat, double currLon) {
        double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 43; // 기준점 X좌표(GRID)
        double YO = 136; // 기1준점 Y좌표(GRID)

        double DEGRAD = Math.PI / 180.0;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);
        LatXLngY rs = new LatXLngY();

        double ra = Math.tan(Math.PI * 0.25 + (currLat) * DEGRAD * 0.5);
        ra = re * sf / Math.pow(ra, sn);
        double theta = currLon * DEGRAD - olon;
        if (theta > Math.PI) theta -= 2.0 * Math.PI;
        if (theta < -Math.PI) theta += 2.0 * Math.PI;
        theta *= sn;
        rs.x = (int)(Math.floor(ra * Math.sin(theta) + XO + 0.5));
        rs.y = (int)(Math.floor(ro - ra * Math.cos(theta) + YO + 0.5));

        return rs;
    }

    class LatXLngY
    {
        public int x;
        public int y;
    }
}
