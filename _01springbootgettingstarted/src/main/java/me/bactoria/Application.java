package me.bactoria;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/*
 * 단순히 SpringApplication을 run 하면 안됨.
 *
 * @SpringBootApplication 대신
 *
 * @EnableAutoConfiguration
 * 을 사용해도 실행 됨. (@SpringBootApplication에 @EnableAutoConfiguration이 포함되어 있음.)
 *
 * SpringApplication을 run 하려면 Configuration이 필요함.
 * @EnableAutoConfiguration은 Configuration을 알아서 Default로 설정해줌.
 */

/*스프링 MVC application을 설정해야하는데..

디스패쳐서블릿,
리스너에다가 WebApplication Context 어떤거 쓸지,
Bean설정파일제공 (어디부터어디까지읽으라고)

해야하는데 여기 아무것도 안보이잖아요. 그게 @EnableAutoConfiguration과 아주 밀접한 관계가 있습니다
*/

/* @ComponentScan
 * 메인메소드를 실행시키는 클래스의 패키지 이하에 위치한 컴포넌트를 scan함.
 * 상위 패키지로 올라가서 scan하지는 않는다.
 * 그렇다고 이 클래스를 java폴더로 이동시키면 비효율적임. 쓸데없는 오만것들을 scan 하기 때문.
 * 적재적소에 위치시켜야 함.
 *
 * ex) 현재 이 클래스는 me.bactoria 패키지에 위치해 있으므로 me.bactoria 이하에 있는 컴포넌트들을 모두 스캔함.
 *
 * */

/* @SpringBootApplication =

 * @SpringBootConfiguration + (사실상 @Configuration 임.)
 * @EnableAutoConfiguration +
 * @ComponentScan +
 *
 */


/* bean 등록은 2단계로 나누어진다.
 *
 * 1. @ComponentScan => Component들을 scan해서 bean 등록 (@Component, @Configuration, @Repository, @Service, @Controller, @RestController)
 * 2. @EnableAutoConfiguration => bean 등록
 *s
 * */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run("--debug");
    }

    /* 빈 등록하면 @SpringBootApplication-@SpringBootConfiguration-@Configuration 에 의해서 @Bean 적용되고
        @ComponentScan이 이걸 찾아서 빈등록함(??)

    @Bean
    public Holoman holoman() {
        Holoman holoman = new Holoman();

        holoman.setName("Created by @ComponentScan");
        holoman.setHowLong(10000);

        return holoman;
    }

    */
}
