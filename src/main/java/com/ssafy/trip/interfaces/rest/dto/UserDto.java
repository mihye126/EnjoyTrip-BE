package com.ssafy.trip.interfaces.rest.dto;

import com.ssafy.trip.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {

    private long id;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String adminCheck;
    private String joinDate;
    private String token;

    public UserDto(User source) {
        this.id = source.getId();
        this.userPw = source.getUser_pw();
        this.userName = source.getUser_name();
        this.userPhone = source.getUser_phone();
        this.userEmail = source.getUser_email();
        this.adminCheck = source.getAdmin_check();
        this.joinDate = source.getJoindate();
        this.token = source.getToken();
    }

}
