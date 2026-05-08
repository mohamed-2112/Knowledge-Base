//package com.mrm.knowledge_base.auth;
//
//import com.mrm.knowledge_base.user.User;
//import com.mrm.knowledge_base.user.UserService;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final UserService userService;
//    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//
//    public CustomOAuth2UserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String email = (String) attributes.get("email");
//        String name = (String) attributes.get("name");
//        String picture = (String) attributes.get("picture");
//        String providerId = (String) attributes.get("sub");
//        User user = userService.findOrCreateOAuth2User(
//                email,
//                name,
//                picture,
//                "GOOGLE",
//                providerId
//        );
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
//        return new DefaultOAuth2User(authorities, attributes, "email");
//    }
//}
