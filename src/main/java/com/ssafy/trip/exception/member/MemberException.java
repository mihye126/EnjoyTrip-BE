package com.ssafy.trip.exception.member;

import com.ssafy.trip.exception.EnjoyTripException;
import org.springframework.http.HttpStatus;

public class MemberException extends EnjoyTripException {
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public MemberException(final String message, final HttpStatus status, final String field) {
        super(message, status, field);
    }
}
