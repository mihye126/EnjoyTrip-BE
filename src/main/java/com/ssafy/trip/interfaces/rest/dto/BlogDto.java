package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor
public class BlogDto {

    private long id;
    private String title;
    private String content;
    private long userId;
    private String userName;

    public BlogDto(Blog source) {
        this.id = source.getId();
        this.title = source.getTitle();
        this.content = source.getContent();
        this.userId = source.getUserId();
        this.userName = source.getUserName();
    }

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
