package com.ssafy.trip.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plan {

    private long id;
    private List<Attraction> attractions;
    private List<User> users;

    public long getId() {
        return id;
    }

    public List<Attraction> getAttractions() {
        return Collections.unmodifiableList(attractions);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
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
