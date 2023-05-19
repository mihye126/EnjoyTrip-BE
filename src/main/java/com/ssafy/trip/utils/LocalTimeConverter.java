package com.ssafy.trip.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"test", "local"})
public class LocalTimeConverter {
    public LocalDateTime getNow() {
        LocalDateTime localNow = LocalDateTime.now().withSecond(0).withNano(0);
        return localNow.atZone(ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();
    }
}
