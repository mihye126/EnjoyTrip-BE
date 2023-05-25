package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Count;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CountDto {
    private long count;
    public CountDto(Count source) {
        this.count = source.getCount();
    }

    public CountDto() {
        count=0L;
    }
}
