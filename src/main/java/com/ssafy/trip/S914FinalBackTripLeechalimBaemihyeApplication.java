package com.ssafy.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class S914FinalBackTripLeechalimBaemihyeApplication {

    public static void main(String[] args) {
        SpringApplication.run(S914FinalBackTripLeechalimBaemihyeApplication.class, args);
    }

}
