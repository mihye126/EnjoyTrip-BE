package com.ssafy.trip.interfaces.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserPasswordUpdateRequest {
    private long id;
    private String password;
}
