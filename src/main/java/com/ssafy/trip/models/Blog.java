package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {
    String contentID;
    String contentTypeID;
    String title;
    String address;
    String firstImage;
    int readcount;
    String overview;
    String userID;
    String userName;

}
