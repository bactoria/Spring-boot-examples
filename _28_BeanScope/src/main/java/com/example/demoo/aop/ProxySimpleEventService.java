package com.example.demoo.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class ProxySimpleEventService implements IBean2 {

    private final SimpleEventService simpleEventService;

    @Override
    public void asd() {
        System.out.println("before deli");
        simpleEventService.asd();
        System.out.println("after deli");
    }
}
