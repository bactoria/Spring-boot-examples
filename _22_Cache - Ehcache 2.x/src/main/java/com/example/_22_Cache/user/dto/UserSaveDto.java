package com.example._22_Cache.user.dto;

import com.example._22_Cache.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveDto {

    private String id;

    private String password;

    private String name;

    @Builder
    public UserSaveDto(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .password(this.password)
                .name(this.name)
                .build();
    }

}
