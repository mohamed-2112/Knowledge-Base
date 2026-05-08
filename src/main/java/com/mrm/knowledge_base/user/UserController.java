package com.mrm.knowledge_base.user;

import com.mrm.knowledge_base.auth.CurrentUserService;
import com.mrm.knowledge_base.user.dto.UserProfileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final CurrentUserService currentUserService;

    public UserController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @GetMapping("/api/users/me")
    public UserProfileResponse me(Authentication authentication) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return UserProfileResponse.from(currentUser);
    }
}
