package me.bactoria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/*
* 패스워드 저장 시 암호화를 해야 하는데,
* security가 제공해줌.
* DB에 저장할 때 메소드 감싸주면됨. CreateAccountRunner 참고
*
* */

@Component
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
