package com.solemate.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiKey {
    @Value("${station-api-key}")
    private String stationKey;


    private String whetherKey;

    @Value("${air-quality-api-key}")
    private String airQualityKey;

    private static ApiKey instance = new ApiKey();

    private ApiKey() {
    }

    public static ApiKey getInstance() {
        if (instance == null) {
            instance = new ApiKey();
        }
        return instance;
    }


}
