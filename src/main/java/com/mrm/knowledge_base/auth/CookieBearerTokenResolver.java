//package com.mrm.knowledge_base.auth;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
//import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.util.WebUtils;
//
//@Component
//public class CookieBearerTokenResolver implements BearerTokenResolver {
//    private final DefaultBearerTokenResolver defaultBearerTokenResolver = new DefaultBearerTokenResolver();
//
//    @Value("${app.auth.cookie-name}")
//    private String cookieName;
//
//    @Override
//    public String resolve(HttpServletRequest request) {
//        String tokenFromHeader = defaultBearerTokenResolver.resolve(request);
//        if (StringUtils.hasText(tokenFromHeader)) {
//            return tokenFromHeader;
//        }
//
//        Cookie cookie = WebUtils.getCookie(request, cookieName);
//
//        if (cookie == null) {
//            return null;
//        }
//        return cookie.getValue();
//    }
//}
