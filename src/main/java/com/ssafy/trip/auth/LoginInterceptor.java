package com.ssafy.trip.auth;

import static com.ssafy.trip.utils.JsonUtils.fromJson;

import com.ssafy.trip.auth.infrastructure.AuthorizationExtractor;
import com.ssafy.trip.auth.infrastructure.JwtTokenProvider;
import com.ssafy.trip.models.LoginUser;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURI();
        System.out.println(url);
        if (url.contains("login")) {
            return true; // 인터셉터를 거치지 않음
        }
        String token = AuthorizationExtractor.extract(request);
        jwtTokenProvider.validateToken(token);

        if (token != null) {
            try {
                String json = new String(Base64.getDecoder().decode(token), "UTF-8");
                LoginUser currentUser = fromJson(json, LoginUser.class);
                if (currentUser.getExpire() < System.currentTimeMillis()) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
                request.setAttribute("currentUser", currentUser);
            } catch (UnsupportedEncodingException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }
        return true;

    }

    private boolean isPreflight(HttpServletRequest request) {
        return request.getMethod().equals(HttpMethod.OPTIONS.toString());
    }
}

