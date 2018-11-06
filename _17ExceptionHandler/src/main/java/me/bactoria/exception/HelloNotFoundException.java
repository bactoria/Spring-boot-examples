package me.bactoria.exception;

import lombok.Getter;

@Getter
public class HelloNotFoundException extends RuntimeException {

    private Long id;

    public HelloNotFoundException(Long id) {
        this.id = id;
    }

}
