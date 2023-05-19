package com.ssafy.trip.auth.ui;

import com.ssafy.trip.auth.application.AuthService;
import com.ssafy.trip.auth.application.AuthorizationException;
import com.ssafy.trip.auth.domain.AuthenticationPrincipal;
import com.ssafy.trip.auth.infrastructure.AuthorizationExtractor;
import com.ssafy.trip.models.LoginUser;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
    private AuthService authService;

    public AuthenticationPrincipalArgumentResolver(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String credentials = AuthorizationExtractor.extract(webRequest.getNativeRequest(HttpServletRequest.class));
        LoginUser user = authService.findMemberByToken(credentials);
        if (user.getId() == null) {
            throw new AuthorizationException();
        }
        return user;
    }
}
