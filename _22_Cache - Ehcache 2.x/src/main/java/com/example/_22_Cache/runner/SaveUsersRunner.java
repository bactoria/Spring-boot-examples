package com.example._22_Cache.runner;

import com.example._22_Cache.user.UserRepository;
import com.example._22_Cache.user.dto.UserSaveDto;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SaveUsersRunner implements ApplicationRunner {

    UserRepository userRepository;

    public SaveUsersRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(UserSaveDto.builder().id("id1").password("pw1").name("name1").build().toEntity());
        userRepository.save(UserSaveDto.builder().id("id2").password("pw2").name("name2").build().toEntity());
        userRepository.save(UserSaveDto.builder().id("id3").password("pw3").name("name3").build().toEntity());
    }
}
