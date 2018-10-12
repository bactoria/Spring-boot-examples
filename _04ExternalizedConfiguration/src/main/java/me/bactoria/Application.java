package me.bactoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(BactoriaProperties.class) // BactoriaProperties.class 에서 @Component 로 Bean 등록하면 이거 안써도 됨.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
