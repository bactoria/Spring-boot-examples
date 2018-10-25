package me.bactoria.runner;

import me.bactoria.user.User;
import me.bactoria.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Order(1)
@Component
public class CreateUserRunner implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        final int N = 55;

        // 유저 N개 생성
        IntStream.rangeClosed(1,N)
                .forEach(index ->
                    userRepository.save(User.builder()
                            .username("bactoria" + index)
                            .password("pass" + index)
                            .email("bactoria" + index + "@gmail.com")
                            .createdTime(LocalDateTime.now())
                            .updatedTime(LocalDateTime.now())
                            .build()));
    }
}
