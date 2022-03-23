package com.solemate.endpoint.openapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
public class WeatherItemsDto {
    private String category;
    private String fcstTime;
    private String fcstValue;
}
