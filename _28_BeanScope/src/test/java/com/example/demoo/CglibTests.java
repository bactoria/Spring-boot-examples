package com.example.demoo;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.util.HashMap;
import java.util.LinkedList;

public class CglibTests {

//    @Test
    public void introduction_to_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Cat.class);

        Cat catO = new Cat();
        String result = new Cat().meow();
        System.out.println("result = " + result);

        enhancer.setCallback((InvocationHandler) (o, method, objects) -> {
            if (method.getDeclaringClass().equals(Cat.class) && method.getReturnType().equals(String.class)) {
                return "something else";
            } else {
                return 10;
            }
        });String s;

        System.out.println("a".hashCode());
        System.out.println("ab".hashCode());
        System.out.println("abc".hashCode());
        System.out.println("abcd".hashCode());
        System.out.println("abcde".hashCode());
        System.out.println("abcdef".hashCode());
        System.out.println("abcdefg".hashCode());
        System.out.println("abcdefgh".hashCode());
        System.out.println("abcdefghi".hashCode());
        System.out.println("abcdefghij".hashCode());

        HashMap hm;
        LinkedList ll;

        Integer a = Integer.MAX_VALUE;
        System.out.println(a.hashCode());
        System.out.println(a>>>16);

        Cat cat = (Cat) enhancer.create();
        System.out.println("cat.meow() = " + cat.meow());
        System.out.println("cat.age() = " + cat.age());
        System.out.println(result instanceof Object);

        ClassLoader loaderO = catO.getClass().getClassLoader();
        System.out.println(loaderO);
        System.out.println(loaderO.getParent());
        System.out.println(loaderO.getClass());

        System.out.println("=================");
        ClassLoader loader = cat.getClass().getClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getClass());

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
