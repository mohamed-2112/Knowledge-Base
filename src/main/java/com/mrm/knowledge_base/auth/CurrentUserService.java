package com.mrm.knowledge_base.auth;

import com.mrm.knowledge_base.user.User;
import com.mrm.knowledge_base.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class CurrentUserService {
    private final UserService userService;


    public CurrentUserService(UserService userService) {
        this.userService = userService;
    }

    public User getCurrentUser(Authentication authentication) {
        String email = extractEmail(authentication);
        return userService.findByEmailOrThrow(email);
    }

    private String extractEmail(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(
                    UNAUTHORIZED,
                    "User is not authenticated"
            );
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof OidcUser oidcUser) {
            return oidcUser.getEmail();
        }
        if(principal instanceof OAuth2User oauth2User) {
            return oauth2User.getAttribute("email");
        }
        throw new ResponseStatusException(UNAUTHORIZED, "User is not authenticated");
    }
}
