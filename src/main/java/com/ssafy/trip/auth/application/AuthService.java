package com.ssafy.trip.auth.application;

import static com.ssafy.trip.utils.JsonUtils.toJson;

import com.ssafy.trip.auth.dto.TokenRequest;
import com.ssafy.trip.auth.dto.TokenResponse;
import com.ssafy.trip.auth.infrastructure.JwtTokenProvider;
import com.ssafy.trip.exception.member.NoSuchMemberException;
import com.ssafy.trip.mapper.UserMapper;
import com.ssafy.trip.models.LoginUser;
import com.ssafy.trip.models.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public LoginUser login(TokenRequest request) {

        try {
            User user = userMapper.findByEmail(request.getEmail())
                .orElseThrow(NoSuchMemberException::new);
            user.checkPassword(request.getPassword());

            LoginUser loginUser=new LoginUser(user);

            loginUser.setExpire(System.currentTimeMillis()+JwtTokenProvider.REFRESH_TOKEN_EXPIRE_MINUTES);
            String refreshToken = jwtTokenProvider.createToken(request.getEmail(),JwtTokenProvider.REFRESH,toJson(loginUser));

            //리팩토링 가능
            loginUser.setExpire(System.currentTimeMillis()+JwtTokenProvider.ACCESS_TOKEN_EXPIRE_MINUTES);
            String accessToken = jwtTokenProvider.createToken(request.getEmail(),JwtTokenProvider.ACCESS, toJson(loginUser));
            loginUser.setAccessToken(accessToken);

            user.setToken(refreshToken);

            userMapper.updateUser(user);
            return loginUser;

        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException();
        }

    }

    public LoginUser findMemberByToken(String credentials) {
        if (!jwtTokenProvider.validateToken(credentials)) {
            return new LoginUser();
        }

        String email = jwtTokenProvider.getPayload(credentials);
        long expire=jwtTokenProvider.getExpire(credentials).toInstant().toEpochMilli();

        try {
            User user = userMapper.findByEmail(email).orElseThrow(NoSuchMemberException::new);
            LoginUser loginUser=new LoginUser(user,credentials,expire);

            return loginUser;
        } catch (Exception e) {
            return new LoginUser();
        }
    }
}
