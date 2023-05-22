package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    private String num;
    private String wdate;
    private String title;
    private String content;
}
