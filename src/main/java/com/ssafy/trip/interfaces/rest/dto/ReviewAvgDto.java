package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.ReviewAvg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewAvgDto {

    private long contentId;
    private double rate;

    public ReviewAvgDto(ReviewAvg source) {
        this.contentId = source.getContentId();
        this.rate = source.getRate();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("contentId", contentId)
            .append("rate", rate)
            .toString();
    }

}
