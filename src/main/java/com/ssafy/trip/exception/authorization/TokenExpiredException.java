package com.ssafy.trip.exception.authorization;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends AuthorizationException {
    private static final String MESSAGE = "로그인이 필요한 서비스입니다.";

    public TokenExpiredException() {
        super(MESSAGE, HttpStatus.UNAUTHORIZED, TOKEN);
    }
}
