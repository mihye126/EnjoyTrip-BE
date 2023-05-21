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

    private long contentID;
    private String content_type_id;
    private String title;
    private String address;
    private String tel;
    private String first_image;
    private String sidoCode;
    private double latitude;
    private double longitude;
    private String overview;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("contentID", contentID)
            .append("contentTypeID", content_type_id)
            .append("title", title)
            .append("address", address)
            .append("tel", tel)
            .append("firstImage", first_image)
            .append("sidoCode", sidoCode)
            .append("latitude", latitude)
            .append("longitude", longitude)
            .append("overview", overview)
            .toString();
    }

}
