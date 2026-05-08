//package com.mrm.knowledge_base.auth;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Duration;
//import java.util.Map;
//
//@RestController
//public class AuthController {
//
//    @Value("${app.auth.cookie-name}")
//    private String cookieName;
//
//    @Value("${app.auth.cookie-secure}")
//    private boolean cookieSecure;
//
//    @Value("${app.auth.cookie-same-site}")
//    private String sameSite;
//
//    @PostMapping("/api/auth/logout")
//    public ResponseEntity<Map<String, String>> logout() {
//        ResponseCookie cookie = ResponseCookie.from(cookieName, "")
//                .httpOnly(true)
//                .secure(cookieSecure)
//                .path("/")
//                .maxAge(Duration.ZERO)
//                .sameSite(sameSite)
//                .build();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(Map.of("message", "Logged out successfully!"));
//    }
//
//}
