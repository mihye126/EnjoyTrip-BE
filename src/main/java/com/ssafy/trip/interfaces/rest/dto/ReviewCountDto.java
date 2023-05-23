package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.ReviewCount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewCountDto {
    private long contentId;
    private int count;

    public ReviewCountDto(ReviewCount source) {
        this.contentId = source.getContentId();
        this.count = source.getCount();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("contentId", contentId)
            .append("count", count)
            .toString();
    }
}
