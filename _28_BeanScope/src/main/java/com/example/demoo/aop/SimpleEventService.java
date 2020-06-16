package com.example.demoo.aop;

import org.springframework.stereotype.Service;

@Service
public class SimpleEventService implements IBean2 {
    @Override
    public void asd() {
        System.out.println("asd()");
    }
}
