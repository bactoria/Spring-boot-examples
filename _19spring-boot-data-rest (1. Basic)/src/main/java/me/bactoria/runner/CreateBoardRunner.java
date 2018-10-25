package me.bactoria.runner;

import me.bactoria.board.Board;
import me.bactoria.board.BoardRepository;
import me.bactoria.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Order(2)
@Component
public class CreateBoardRunner implements ApplicationRunner {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        final int N = 55;

        // 게시글 N개 생성
        IntStream.rangeClosed(1, N)
                .forEach(index ->
                        boardRepository.save(Board.builder()
                                .title("제목" + index)
                                .content("내용" + index)
                                .user(userRepository.findAll().get(index - 1))
                                .createdTime(LocalDateTime.now())
                                .updatedTime(LocalDateTime.now())
                                .build()));
    }
}
