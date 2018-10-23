package me.bactoria;

import me.bactoria.exception.ByeException;
import me.bactoria.exception.HelloException;
import me.bactoria.exception.WorldException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 기본 에러 핸들링은 BasicErrorController 가 담당함.
//

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        throw new HelloException(); // 500 Error 임. GlobalExceptionHandler 에 의해 처리됨.
    }

    @GetMapping("/world")
    public String world() {
        throw new WorldException(); // 500 Error. 아래의 HelloExceptionHandler 에 의해 처리됨.
    }

    @GetMapping("/bye")
    public String bye() {
        throw new ByeException(); // 500 Error. 이 에러는 따로 핸들러가 없음. 500에러 페이지를 띄워줄거임. static/error/5xx.html
    }

    // JSON 응답.
    // 지역적으로 적용. HelloController 안에서만 적용됨.
    // 전역으로 하고싶으면 GlobalExceptionHadler.class 참고.
    @ExceptionHandler(WorldException.class)
    public @ResponseBody String HelloErrorHandler(WorldException e) {

        //그냥 json형태로 만들었음.
        return "{\"message\" : \"error.엥.앵\", \"reason\" : \"이유는이유. world world~\"}";
    }

}
