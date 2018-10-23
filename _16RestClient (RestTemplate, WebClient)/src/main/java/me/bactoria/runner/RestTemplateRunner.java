package me.bactoria.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateRunner implements ApplicationRunner {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        RestTemplate restTemplate = restTemplateBuilder.build();

        // 0
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        /* 컨트롤러 3개 실행. 각 컨트롤러는 3초, 5초, 6초가 걸리는 Task.*/

        // 3초 ( 0 ~ 3 )
        String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
        System.out.println("This is helloResult : " + helloResult + " (using RestTemplate)");


        // 5초 ( 3 ~ 8 )
        String worldResult = restTemplate.getForObject("http://localhost:8080/world", String.class);
        System.out.println("This is worldResult : " + worldResult + " (using RestTemplate)");


        // 6초 ( 8 ~ 14 )
        String byeResult = restTemplate.getForObject("http://localhost:8080/bye", String.class);
        System.out.println("This is byeResult : " + byeResult + " (using RestTemplate)");


        // => 총 14초
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
