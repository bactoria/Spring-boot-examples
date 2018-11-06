package me.bactoria.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    HELLO_NOT_FOUND("AC_001", "해당 Hello를 찾을 수 없습니다.", 404),
    WORLD("AC_002", "WORLD 워어얼~드", 400);

    private String code;
    private String message;
    private int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
