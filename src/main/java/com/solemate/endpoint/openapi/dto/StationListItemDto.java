package com.solemate.endpoint.openapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
public class StationListItemDto {
    private double dmX;
    private String stationName;
    private double dmY;
}
