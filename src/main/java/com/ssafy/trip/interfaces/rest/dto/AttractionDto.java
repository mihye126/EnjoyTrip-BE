package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Attraction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@Getter
public class AttractionDto {

    private long contentID;
    private String conTentTypeId;
    private String title;
    private String address;
    private String tel;
    private String firstImage;
    private String sidoCode;
    private double latitude;
    private double longitude;
    private String overview;


    public AttractionDto(Attraction source) {
        this.contentID = source.getContentID();
        this.conTentTypeId = source.getContentTypeId();
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
            .append("contentTypeID", conTentTypeId)
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
