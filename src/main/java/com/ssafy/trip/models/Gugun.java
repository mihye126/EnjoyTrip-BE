package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Gugun {

    private String name;
    private String code;
    private String sidoCode;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("name", name)
            .append("code", code)
            .append("sidoCode", sidoCode)
            .toString();
    }
}
