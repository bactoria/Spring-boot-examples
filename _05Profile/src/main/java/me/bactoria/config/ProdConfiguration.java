package me.bactoria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod") //profile이 prod일 때만 실행시켜주세요~ (hello 빈 등록은 profile이 prod 일때만 일어남)
@Configuration
public class ProdConfiguration {

    @Bean
    public String hello() {
        return "ProdHello";
    }


}
