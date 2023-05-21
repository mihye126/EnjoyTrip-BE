package com.ssafy.trip.interfaces.rest.dto;

import static com.ssafy.trip.utils.JsonUtils.fromJson;

import com.ssafy.trip.models.Plan;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor
public class PlanDto {

    private long id;
    private long userId;
    private String userName;
    private List<Long> attractions;
    public PlanDto(Plan source) {
        this.id = source.getId();
        this.userId=source.getUserId();
        this.userName=source.getUserName();
        this.attractions = Arrays.asList(fromJson(source.getAttractions(),Long[].class));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("userId", userId)
            .append("attractions", attractions)
            .toString();
    }

}

