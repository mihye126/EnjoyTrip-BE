package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Sido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor
public class SidoDto {

    private String name;
    private String code;

    public SidoDto(Sido source) {
        this.code=source.getCode();
        this.name=source.getName();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("name", name)
            .append("code", code)
            .toString();
    }
}
