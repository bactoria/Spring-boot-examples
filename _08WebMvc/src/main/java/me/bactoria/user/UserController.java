package me.bactoria.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("hello")
    public @ResponseBody String hello() {
        /*@RestController 일 때는 @ResponseBody 생략 가능.

        @Controller일 때 @ResponseBody를 생략한다면
        MessageConverter가 사용되는게 아니라 ViewNameResolver가 사용되서 이름에 해당하는 View를 찾으려고 할 것임.*/
        return "hello";
    }

    /* HttpMessageConverters
    *
    * 어떤 요청을 받았는지 / 어떤 응답을 보내야 하는지에 따라
    * 사용하는 HttpMessageConverter가 달라진다.
    *
    * - Request
    *   Json Request (Content Type:Json, 본문도 Json) 가 들어온다면
    *   JsonMessageConverter가 사용되어서 Json Message를 user 로 Convert 해준다.
    *
    * - Response
    *   응답할 때 User 라는 객체로 보낼 수 없다.
    *   Http 자체가 문자이니까.
    *   이걸 변환해야 하는데...
    *   기본적으로 composition type(String, Integer...등을 제외한 객체)
    *   JsonMessageConverter가 사용된다.
    *
    *   리턴 타입이 String 이라면 StringMessageConverter가 사용된다.
    *   리턴 타입이 int 일 때도 StringMessageConverter가 사용된다.
    *
    * */
    @PostMapping("/users")
    public @ResponseBody User create(@RequestBody User user) {
        return user;
    }

    /*  View Resolver

    ContentNegotiatingViewResolver 란?

    들어오는 request의 accept 헤더에 따라 response가 달라진다.
    accept 헤더란 것은 브라우저가 어떠한 타입의 응답을 원한다 라고 서버에게 알려주는 것임.
    accept 헤더에 따라 응답이 달라질 수 있음.



    */
}
