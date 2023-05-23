package com.ssafy.trip.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCount {
    private long contentId;
    private int count;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("contentId", contentId)
            .append("count", count)
            .toString();
    }
}
