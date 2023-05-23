package com.ssafy.trip.models;

import lombok.Getter;

@Getter
public enum MyBatisResult {
    SUCCEED(1),FAIL(0);

    private final int  result;

    MyBatisResult(int result) {
        this.result = result;
    }
}
