package com.ssafy.trip.exception.space;

import com.ssafy.trip.exception.EnjoyTripException;
import org.springframework.http.HttpStatus;

public class SpaceException extends EnjoyTripException {
    public static final String SPACE_ID = "spaceId";
    public static final String DAY_OF_WEEK = "dayOfWeek";

    public SpaceException(final String message, final HttpStatus status, final String field) {
        super(message, status, field);
    }
}
