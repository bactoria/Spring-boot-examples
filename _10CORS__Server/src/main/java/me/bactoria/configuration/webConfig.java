package me.bactoria.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Override // Override 단축키 : Ctrl + O
    public void addCorsMappings(CorsRegistry registry) {

        //모든 uri 를 http://localhost:18080에게 허용하겠다.
        /*
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:18080");
        */

        // Get Method로 /hello에 요청 시 모두에게 허용하겠다.
        registry.addMapping("/hello")
                .allowedMethods(HttpMethod.GET.name())
                .allowedOrigins("*");
    }
}
