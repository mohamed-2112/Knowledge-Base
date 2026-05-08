package com.mrm.knowledge_base.user.dto;


import com.mrm.knowledge_base.user.User;

public record UserProfileResponse(
        Long id,
        String email,
        String name,
        String picture,
        String role
) {
    public static UserProfileResponse from(User user){
        return new UserProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPicture(),
                user.getRole().name()
        );
    }
}
