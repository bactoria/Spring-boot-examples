package me.bactoria.error;

import lombok.extern.slf4j.Slf4j;
import me.bactoria.exception.HelloNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// 전역적으로 적용.

@Slf4j
@ResponseBody
@ControllerAdvice // 빈등록
public class ExceptionHandlerController {

    @ExceptionHandler(HelloNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse HelloError(HelloNotFoundException e) {
        final ErrorCode helloNotFound = ErrorCode.HELLO_NOT_FOUND;
        log.error(helloNotFound.getMessage() + " id:" + e.getId());
        return getBuild(helloNotFound);
    }

    private ErrorResponse getBuild(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getStatus())
                .build();
    }

}