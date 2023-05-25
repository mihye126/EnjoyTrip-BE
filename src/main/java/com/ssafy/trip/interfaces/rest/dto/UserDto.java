package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@Getter
public class UserDto {

    private long id;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String adminCheck;
    private String joinDate;

    public UserDto(User source) {
        this.id = source.getId();
        this.userName = source.getUser_name();
        this.userPhone = source.getUser_phone();
        this.userEmail = source.getUser_email();
        this.adminCheck = source.getAdmin_check();
        this.joinDate = source.getJoindate();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("userName", userName)
            .append("userPhone", userPhone)
            .append("userEmail", userEmail)
            .append("adminCheck", adminCheck)
            .append("joinDate", joinDate)
            .toString();
    }


}
