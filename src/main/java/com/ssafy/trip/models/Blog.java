package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import springfox.documentation.spring.web.json.Json;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private long id;
    private String title;
    private String content;
    private long userId;
    private String userName;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("userName", userName)
            .append("title", title)
            .append("content", content)

            .toString();
    }
}
