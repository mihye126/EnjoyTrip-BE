
package com.ssafy.trip.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String user_pw;
    private String user_name;
    private String user_phone;
    private String user_email;
    private String admin_check;
    private String joindate;
    private String token;

    public boolean checkPassword(String password){
        return password==this.user_pw;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("userName", user_name)
            .append("userPhone", user_phone)
            .append("userEmail", user_email)
            .append("adminCheck", admin_check)
            .append("joinDate", joindate)
            .toString();
    }

}
