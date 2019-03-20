package me.bactoria._25_OAuth2.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String role;

    @Column
    private String phone;

    @Builder
    public User(String id, String password, String name, String role, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.phone = phone;
    }
}
