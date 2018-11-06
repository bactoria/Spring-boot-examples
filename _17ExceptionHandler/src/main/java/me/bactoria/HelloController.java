package me.bactoria;

import lombok.extern.slf4j.Slf4j;
import me.bactoria.error.ErrorCode;
import me.bactoria.error.ErrorResponse;
import me.bactoria.exception.ByeException;
import me.bactoria.exception.HelloNotFoundException;
import me.bactoria.exception.WorldException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 기본 에러 핸들링은 BasicErrorController 가 담당함.
//

@Slf4j
@Controller
public class HelloController {

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable long id) {
        throw new HelloNotFoundException(id);
    }

    @GetMapping("/world")
    public String world() {
        throw new WorldException(); // 아래의 WorldExceptionHandler 에 의해 처리됨.
    }

    @GetMapping("/bye")
    public String bye() {
        throw new ByeException(); // 500 Error. 이 에러는 따로 핸들러가 없음. 500에러 페이지를 띄워줄거임. static/error/5xx.html
    }

    // JSON 응답.
    // 지역적으로 적용. HelloController 안에서만 적용됨.
    // 전역으로 하고싶으면 ExceptionHandlerController.class 참고.
    @ExceptionHandler(WorldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse worldError(WorldException e) {
        final ErrorCode worldError = ErrorCode.WORLD;
        log.error(worldError.getMessage());
        return ErrorResponse.builder()
                .code(worldError.getCode())
                .message(worldError.getMessage())
                .status(worldError.getStatus())
                .build();
    }
}
