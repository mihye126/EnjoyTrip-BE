package com.ssafy.trip.auth.ui;

import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.auth.application.AuthService;
import com.ssafy.trip.auth.dto.TokenRequest;
import com.ssafy.trip.auth.dto.TokenResponse;
import com.ssafy.trip.models.LoginUser;
import com.ssafy.trip.web.ApiResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/token")
    public LoginUser login(@RequestBody TokenRequest request) {
        LoginUser loginUser = authService.login(request);
        return authService.login(request);
    }
}
