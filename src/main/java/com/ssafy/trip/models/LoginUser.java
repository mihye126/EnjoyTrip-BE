package com.ssafy.trip.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginUser {

    private Long id;
    private String userName;
    private String userPhone;
    private String userEmail;
    private long expire;
    private String accessToken;

    public LoginUser(User user, String credentials, long expire) {
        this.userName = user.getUser_name();
        this.userPhone = user.getUser_phone();
        this.userEmail = user.getUser_email();
        this.accessToken = accessToken;
        this.expire = expire;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= expire;
    }


    public void setExpire(long expire) {
        this.expire = expire;
    }
    
    public LoginUser(User user) {
        this.id = user.getId();
        this.userName = user.getUser_name();
        this.userPhone = user.getUser_phone();
        this.userEmail = user.getUser_email();
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("userName", userName)
            .append("userPhone", userPhone)
            .append("userEmail", userEmail)
            .append("expire", expire).toString();
    }



}
