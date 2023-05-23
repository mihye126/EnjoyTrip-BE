package com.ssafy.trip.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchParameter {

    NAME("name"),CODE("code");

    private final String param;

}
