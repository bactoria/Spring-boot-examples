package me.bactoria.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // Builder 때문에 기본생성자 생성 안됨. 명시적으로 추가 해줘야 함. JPA에서 데이터 받아올 때 기본생성자에 getter로 받아오는 것 같음.
@Getter
@Entity
@Table
public class User {

    @Id @Column @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private LocalDateTime createdTime;

    @Column
    private LocalDateTime updatedTime;

    @Builder
    public User(String username, String password, String email, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
