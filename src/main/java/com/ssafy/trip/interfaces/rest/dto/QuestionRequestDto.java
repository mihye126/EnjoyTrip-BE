package com.ssafy.trip.interfaces.rest.dto;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class QuestionRequestDto implements Serializable {
    private String days;
    private String city;
}
