package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor
public class NoticeDto {
    private long id;
    private String writeDate;
    private String title;
    private String content;

    public NoticeDto(Notice source) {
        this.id = source.getId();
        this.writeDate = source.getWriteDate();
        this.title = source.getTitle();
        this.content = source.getContent();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("writeDate", writeDate)
            .append("title", title)
            .append("content", content)
            .toString();
    }
}
