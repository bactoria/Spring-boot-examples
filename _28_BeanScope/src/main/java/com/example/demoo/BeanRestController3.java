package com.example.demoo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController
public class BeanRestController3 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @GetMapping("/beans3")
    public ResponseEntity<BeanResponseDto> beans() {

        return ResponseEntity.ok(BeanResponseDto.builder()
                .requestBean1((String) applicationContext.getBean("myRequestBean"))
                .build());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
