package com.mrm.knowledge_base.auth;

import com.mrm.knowledge_base.user.User;
import com.mrm.knowledge_base.user.UserRepository;
import com.mrm.knowledge_base.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
//
//@Component
//public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
//    private final JwtService jwtService;
//    private final UserService userService;
//
//    @Value("${app.frontend.redirect-url}")
//    private String frontendRedirectUrl;
//
//    @Value("${app.auth.cookie-name}")
//    private String cookieName;
//
//    @Value("${app.jwt.expiration-seconds}")
//    private long expirationSeconds;
//
//    @Value("${app.auth.cookie-secure}")
//    private boolean cookieSecure;
//
//    @Value("${app.auth.cookie-same-site}")
//    private String sameSite;
//
//
//    public OAuth2LoginSuccessHandler(JwtService jwtService, UserService userService) {
//        this.jwtService = jwtService;
//        this.userService = userService;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        assert oAuth2User != null;
//        String email = oAuth2User.getAttribute("email");
//        String name = oAuth2User.getAttribute("name");
//        String picture = oAuth2User.getAttribute("picture");
//        String providerId = oAuth2User.getAttribute("sub");
//
//        User user = userService.findOrCreateOAuth2User(email, name, picture, "GOOGLE", providerId);
//        String token = jwtService.generateToken(user);
//
//        ResponseCookie cookie = ResponseCookie.from(cookieName, token)
//                .httpOnly(true)
//                .secure(cookieSecure)
//                .path("/")
//                .maxAge(Duration.ofSeconds(expirationSeconds))
//                .sameSite(sameSite)
//                .build();
//        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
////        response.sendRedirect(frontendUrl);
////        response.sendRedirect("http://localhost:8080/?token=" + token);
//        response.sendRedirect("http://localhost:3000/oauth2/success" );
//    }
//}



@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${app.frontend.redirect-url}")
    private String frontendSuccessUrl;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        response.sendRedirect(frontendSuccessUrl);
    }
}
