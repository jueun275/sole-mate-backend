package com.solemate.endpoint.openapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
public class StationItemsDto {
    private double dmX;
    private String stationName;
    private double dmY;
}
