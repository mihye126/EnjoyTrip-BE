package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Attraction {

    private long contentID;
    private String contentTypeId;
    private String title;
    private String address;
    private String tel;
    private String firstImage;
    private String sidoCode;
    private String gugunCode;
    private double latitude;
    private double longitude;
    private String overview;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("contentID", contentID)
            .append("contentTypeID", contentTypeId)
            .append("title", title)
            .append("address", address)
            .append("tel", tel)
            .append("firstImage", firstImage)
            .append("sidoCode", sidoCode)
            .append("gugunCode", gugunCode)
            .append("latitude", latitude)
            .append("longitude", longitude)
            .append("overview", overview)
            .toString();
    }

}
