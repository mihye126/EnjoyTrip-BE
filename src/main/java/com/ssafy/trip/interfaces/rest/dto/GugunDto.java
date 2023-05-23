package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Gugun;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor
public class GugunDto {
    private String name;
    private String code;
    private String sidoCode;

    public GugunDto(Gugun source) {
        this.name = source.getName();
        this.code = source.getCode();
        this.sidoCode = source.getSidoCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("name", name)
            .append("code", code)
            .append("sidoCode", sidoCode)
            .toString();
    }

}
