package com.mrm.knowledge_base.configuration;

import com.mrm.knowledge_base.auth.CustomOidcUserService;
import com.mrm.knowledge_base.auth.OAuth2LoginSuccessHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Set;

@Configuration
public class OauthSecurityConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http,
                                                      OAuth2LoginSuccessHandler successHandler,
                                                      CustomOidcUserService customOidcUserService) throws Exception {
        CsrfTokenRequestAttributeHandler csrfRequestHandler =
                new CsrfTokenRequestAttributeHandler();
        http
                .cors(cors -> {})
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(csrfRequestHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/error").permitAll()
                        .requestMatchers("/oauth2/**", "/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/csrf").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userinfo -> userinfo
                                .oidcUserService(customOidcUserService)
                        )
                        .successHandler(successHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .deleteCookies("JSESSIONID", "XSRF-TOKEN")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                );
        return http.build();
    }

//
//
//    @Bean
//    @Order(1)
//    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http, CookieBearerTokenResolver cookieBearerTokenResolver) throws Exception{
//        CsrfTokenRequestAttributeHandler csrfRequestHandler = new CsrfTokenRequestAttributeHandler();
//
//        RequestMatcher csrfRequiredMatcher = request -> {
//            Set<String> safeMethods = Set.of("GET", "HEAD", "TRACE",  "OPTIONS");
//            return !safeMethods.contains(request.getMethod());
//        };
//        http.
//                securityMatcher("/api/**").
//                cors(cors->{}).
//                csrf(csrf -> csrf
//                        .requireCsrfProtectionMatcher(csrfRequiredMatcher)
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(csrfRequestHandler)
//                )
//                .sessionManagement(
//                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                ).
//                authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET,"/api/csrf").permitAll()
//                        .requestMatchers("/api/auth/logout").authenticated()
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
////                .oauth2ResourceServer(
////                        oauth2 -> oauth2
////                                .bearerTokenResolver(cookieBearerTokenResolver)
////                                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
////                )
//        ;
//        return http.build();
//    }
//
//    @Bean
//    @Order(1)
//    SecurityFilterChain oauth2LoginSecurityFilterChain(HttpSecurity http, OAuth2LoginSuccessHandler successHandler) throws Exception{
//        http.
//                csrf(AbstractHttpConfigurer::disable).
//                cors(cors->{}).
//                authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/login", "/error", "/oauth2/**", "/login/oauth2/**").permitAll()
//                        .anyRequest().permitAll()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .successHandler(successHandler));
//        return http.build();
//    }
//
//
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        authoritiesConverter.setAuthoritiesClaimName("role");
//        authoritiesConverter.setAuthorityPrefix("ROLE_");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
//        return jwtAuthenticationConverter;
//    }


}
