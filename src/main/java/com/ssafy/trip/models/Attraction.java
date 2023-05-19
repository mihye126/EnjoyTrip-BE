package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Attraction {

    private String contentID;
    private String contentTypeID;
    private String title;
    private String address;
    private String tel;
    private String firstImage;
    private String sidoCode;
    private String latitude;
    private String longitude;
    private String overview;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("contentID", contentID)
            .append("contentTypeID", contentTypeID)
            .append("title", title)
            .append("address", address)
            .append("tel", tel)
            .append("firstImage", firstImage)
            .append("sidoCode", sidoCode)
            .append("latitude", latitude)
            .append("longitude", longitude)
            .append("overview", overview)
            .toString();
    }

}
