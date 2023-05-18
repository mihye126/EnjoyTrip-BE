package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Attraction;
import com.ssafy.trip.models.Plan;
import com.ssafy.trip.models.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor
public class PlanDto {

    private long id;
    private List<Attraction> attractions;
    private List<User> users;

    public PlanDto(Plan source) {
        this.id = source.getId();
        this.attractions = source.getAttractions();
        this.users = source.getUsers();

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("attractions", attractions)
            .append("users", users)
            .toString();
    }

}
