package me.bactoria.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientRunner implements ApplicationRunner {

    //@Autowired
    //WebClient.Builder webClient;

    WebClient webClient;

    // @Autowired 한거랑 기능 똑같음.
    // 빈으로 등록한 클래스(WebClientRunner.class) 의 생성자의 파라미터(WebClient.Builder)가 빈에 등록되있을 경우 그 빈을 가져온다.
    WebClientRunner(WebClient.Builder webClient) {
        this.webClient = webClient
                // ### Customizing ###
                //.baseUrl("http://localhost:8080")
                .build();

        /* baseUrl 주석처리 했는데...
        *  config 로 baseUrl 을 처리해줬기 때문임.
        *  (config/WebClientCustomizerConfig.class)
        *  위처럼 커스터마이징 해도 되고, config 만들어도 된다~
        *  */
    }


    // WebClient를 쓰면
    // /hello 에 대한 응답이 왔을 때 /bye를 호출 하는 등의 유연한 응용이 가능 함.

    @Override
    public void run(ApplicationArguments args) throws Exception {

/*
        @Autowired했으면 build 해줘야 하는데,
        생성자에서 build 로직을 처리했음.

        WebClient webClient = this.webClient
                                                // ### Customizing ###
                                                .baseUrl("http://localhost:8080")
                                                .build();
*/

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();


        // url로 요청을 처리한게 아님. Mono를 만든 것일 뿐임.
        // 이 Mono를 동작시키려면 subscribe 해야 함.
        // 자세한건 Mono를 공부해야 함.

        Mono<String> helloMono = webClient.get().uri("/hello")
                .retrieve() //
                .bodyToMono(String.class);// String 타입으로 변환.

        Mono<String> worldMono = webClient.get().uri("/world")
                .retrieve() //
                .bodyToMono(String.class);// String 타입으로 변환.

        Mono<String> byeMono = webClient.get().uri("/bye")
                .retrieve() //
                .bodyToMono(String.class);// String 타입으로 변환.


        // 3초 (2 ~ 5)
        helloMono.subscribe(s -> { // Callback
            System.out.println(s);
            PrintStopWatch("hello", stopWatch);
        });

        // 5초 (2 ~ 7)
        worldMono.subscribe(s -> { // Callback
            System.out.println(s);
            PrintStopWatch("world", stopWatch);
        });

        // 6초 (2 ~ 8)
        byeMono.subscribe(s -> { // Callback
            System.out.println(s);
            PrintStopWatch("bye", stopWatch);
        });


        // 여기다 찍어버리면... 그냥 위에것들 호출만 시키고 난 시간임.
        // 2초 정도 걸리네(0 ~ 2)
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        stopWatch.start();

        System.out.println("=================");
        System.out.println("All of Mono is Subscribed");
        System.out.println("=================");

    }

    private void PrintStopWatch(String name, StopWatch stopWatch) {
        if (stopWatch.isRunning()) {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
            System.out.println("This was " + name + "'s timer");
            stopWatch.start();
        }
    }
}
