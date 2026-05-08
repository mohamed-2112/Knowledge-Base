package com.mrm.knowledge_base.testingAPI;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class Testapi {


    @GetMapping("/")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        if (Objects.isNull(principal)) {
            Object test = "Hello World";
            Map<String, Object> map = new HashMap<>();
            map.put("test", test);
            return map;
        }
        return principal.getAttributes();
    }


    @GetMapping("/api/admin/test")
    public String adminTest() {
        return "the admin is working";
    }

    @GetMapping("/api/user/test")
    public String userTest() {
        return "the user is working";
    }

    @PostMapping("/api/test/csrf")
    public Map<String, Object> csrfTest() {
        return Map.of("MESSAGE", "it has passed");
    }

}
