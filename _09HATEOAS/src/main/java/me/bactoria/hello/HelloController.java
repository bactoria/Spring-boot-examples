package me.bactoria.hello;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/*
* 진정한 Rest API 라면 링크도 추가해줘야지 !!
*
* https://spring.io/understanding/HATEOAS
* https://spring.io/guides/gs/rest-hateoas/
* https://docs.spring.io/spring-hateoas/docs/current/reference/html/
*
* */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Resource<Hello> hello() {

        Hello hello = new Hello();
        hello.setPrefix("Hello, ");
        hello.setName("Junoh");

        /*Resource 쓰려면 hateoas관련 dependency 추가해야 함*/
        Resource<Hello> helloResource = new Resource<>(hello);
        helloResource.add(linkTo(methodOn(HelloController.class).hello()).withSelfRel()); //셀프 링크 추가
        // HelloController.class의 hello() method의 링크(http://도메인/hello) 를 SelfRel로 추가

        return helloResource;
    }

}
