package me.bactoria._25_OAuth2.user.dto;

import lombok.Builder;
import me.bactoria._25_OAuth2.user.User;

public class UserCreateRequestDto {

    private String id;
    private String password;
    private String name;
    private String role;
    private String phone;

    @Builder
    public UserCreateRequestDto(String id, String password, String name, String role, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.phone = phone;
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .phone(this.phone)
                .role(this.role)
                .build();
    }
}
