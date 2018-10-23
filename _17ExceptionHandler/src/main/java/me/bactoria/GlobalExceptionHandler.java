package me.bactoria;

import me.bactoria.exception.HelloException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 전역적으로 적용.

@ControllerAdvice // 빈등록
public class GlobalExceptionHandler {

    @ExceptionHandler(HelloException.class)
    public @ResponseBody String HelloError(HelloException e) {
        return "{\"message\" : \"error.엥.앵\", \"reason\" : \"이유는이유. HELLO\"}";
    }
}
