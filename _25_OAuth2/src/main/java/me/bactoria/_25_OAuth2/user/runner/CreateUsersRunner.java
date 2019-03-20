package me.bactoria._25_OAuth2.user.runner;


import me.bactoria._25_OAuth2.user.UserRepository;
import me.bactoria._25_OAuth2.user.dto.UserCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class CreateUsersRunner implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUsersRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        IntStream.rangeClosed(1, 10).parallel().forEach(x -> userRepository.save(UserCreateRequestDto.builder()
                .id("user" + x)
                .password(passwordEncoder.encode("pass"))
                .build()
                .toEntity()));

    }
}
