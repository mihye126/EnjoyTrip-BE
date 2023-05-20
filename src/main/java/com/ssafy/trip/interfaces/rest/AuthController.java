package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.services.AuthService;
import com.ssafy.trip.interfaces.rest.dto.TokenRequest;
import com.ssafy.trip.models.LoginUser;
import com.ssafy.trip.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ApiResult<LoginUser> login(@RequestBody TokenRequest request) {
        return succeed(authService.login(request));
    }

    @PostMapping("/logout")
    public ApiResult<String> logout(@RequestBody Long id) {
        return succeed(authService.logout(id));
    }
}
