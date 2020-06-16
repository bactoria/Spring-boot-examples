package com.example.demoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Cat.class);

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        String result = new Cat().meow();
        System.out.println("result = " + result);

        enhancer.setCallback((InvocationHandler) (o, method, objects) -> {
            if (method.getDeclaringClass().equals(Cat.class) && method.getReturnType().equals(String.class)) {
                return "something else";
            } else {
                return 10;
            }
        });

        Cat cat = (Cat) enhancer.create();
        System.out.println("cat.meow() = " + cat.meow());
        System.out.println("cat.age() = " + cat.age());

        SpringApplication.run(DemoApplication.class, args);
    }


    public static class Cat {
        public String meow() {
            return "meeoowwww";
        }

        public int age() {
            return 5;
        }
    }

}
