package com.solemate.endpoint.openapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
public class WeatherDto {
    private String totalCount;
    private String pageNo;
    private String numOfRows;
    private List<WeatherItemsDto> item;
}
