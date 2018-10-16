package me.bactoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*

   로깅 퍼사드
    1. Commons Logging
    2. SLF4j

   로거
    1. JUL
    2. Log4J2
    3. Logback

*/

/*
로깅 퍼사드 - 로깅을 하는 애들이 아님. Logger API들을 추상화해놓은 Interface.
Framework 들은 로깅 퍼사드를 이용하여 개발한다. 왜냐? 사용자들이 무슨 로거를 쓸지 모르니까.
만약 Framework 에서 JUL을 쓴다면 그 Framework 사용자들은 모두 JUL 을 이용하여야 할 것이다.
장점 : 밑에 있는 로거들을 바꾸어 낄 수 있게 해준다.

1. Commons Logging  
2. SLF4j (Simple Logging Facade for Java) - Commons Logging 보다 구조적으로 더 안전함.

스프링 부트는 Commons Logging 을 쓴다.
잠깐! SLF4j가 더 안전하다면서 왜 Commons Logging을 쓰냐???

Spring Framework Core 가 만들어질 때에는 SLF4j가 없어서 Commons Logging을 쓰고 있었다.
그래서 Spring Boot 1.x 대에서는
Spring Core 에 대한 Commons Logging 의존성을 exclusion 시키고 SLF4j를 inclusion 시켜서 썼음. 또, 의존성 설정이 필요했음.
(Commons Logging 은 Runtime 도중에 로거를 찾는데, SLF4j는 Compile 시에 로거를 찾아놓는다. 이에, 추가적인 설정 필요)

Spring 5 에서 exclusive 하지 않아도 되게끔 자체 내에서
Spring-JCL(자카르타 Commons Logging) 모듈 만듬.
Commons Logging을 Compile 시점에 SLF4j or Log4j2로 변경하는 기능을 가진 모듈임.
=> pom.xml에 exclusion 안해도 됨.
*/

/*
정리하자면..
스프링부트는 Commons Logging을 쓴다.
우리는 Commons Logging을 써도 되고 SLF4j를 써도 된다.
결국에는 Commons Logging -> SLF4j -> Logback으로 보내기 때문.
최종적으로 Logback이 찍히는 것이다.
스프링부트를 처음 프로젝트를 만들었을때 로그 찍는거는 Logback이 찍은 것이다.
Logback으로 가는 과정이 얽히고 섥혀서 그런거지.. 최종적으로는 Logback으로 찍혔다는점~...

의존성을 보면 이해할 수 있다

지금 당장 오른쪽 Maven Projects 열어보셈.
Dependencies 에 보면 spring-boot-starter-logging 에 어떤 것들이 들어왔는지..
JUL to SLF4j, Log4j to SLF4j, Logback 이렇게 들어옴.
엥...?
*/

/*
로거
1. JUL
2. Log4J2
3. Logback
*/


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
