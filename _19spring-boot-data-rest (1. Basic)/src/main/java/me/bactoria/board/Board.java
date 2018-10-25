package me.bactoria.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bactoria.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table
public class Board {

    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @OneToOne
    private User user;

    @Column
    private LocalDateTime createdTime;

    @Column
    private LocalDateTime updatedTime;

    @Builder
    public Board(String title, String content, User user, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public void setCreatedTime() {
        this.createdTime = LocalDateTime.now();
    }

    public void update(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.updatedTime = LocalDateTime.now();
    }

}
