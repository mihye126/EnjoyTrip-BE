package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Attraction;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@Getter
public class AttractionDto {

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


    public AttractionDto(Attraction source) {
        this.contentID = source.getContentID();
        this.contentTypeID = source.getContentTypeID();
        this.title = source.getTitle();
        this.address = source.getAddress();
        this.tel = source.getTel();
        this.firstImage = source.getFirstImage();
        this.sidoCode = source.getSidoCode();
        this.latitude = source.getLatitude();
        this.longitude = source.getLongitude();
        this.overview = source.getOverview();
    }

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
