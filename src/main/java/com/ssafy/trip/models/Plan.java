package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Plan {

    private long id;
    private long userId;
    private String userName;
    private String attractions;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("attractions", attractions)
            .append("userId", userId)
            .append("userName", userName)
            .toString();
    }

}
