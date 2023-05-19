package com.ssafy.trip.exception.authorization;

import com.ssafy.trip.exception.EnjoyTripException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends EnjoyTripException {
    public static final String TOKEN = "accessToken";
    public static final String AUTHORITY_ON_MAP = "authorityOnMap";

    public AuthorizationException(final String message, final HttpStatus status, final String field) {
        super(message, status, field);
    }
}
