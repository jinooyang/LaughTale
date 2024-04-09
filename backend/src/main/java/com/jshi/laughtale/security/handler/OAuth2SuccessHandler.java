package com.jshi.laughtale.security.handler;

import com.jshi.laughtale.security.jwt.JwtProcessor;
import com.jshi.laughtale.security.oauth2.OAuth2UserPrincipal;
import com.jshi.laughtale.security.oauth2.UserInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final String BASE_URL = "https://j10a705.p.ssafy.io/login";
    private final String ACCESS_HEADER = "accessToken";

    private final JwtProcessor jwtProcessor;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("in success handler");
        OAuth2UserPrincipal principal = (OAuth2UserPrincipal) authentication.getPrincipal();

        UserInfo userInfo = principal.getUserInfo();

        String token = jwtProcessor.createJwtToken(userInfo.getEmail(), userInfo.getRole().value());

        String url = createUrl(token);
        response.sendRedirect(url);
    }

    private String createUrl(String accessToken) {
        return UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam(ACCESS_HEADER, accessToken)
                .build()
                .encode()
                .toUriString();
    }
}
