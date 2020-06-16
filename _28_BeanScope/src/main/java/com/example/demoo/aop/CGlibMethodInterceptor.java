package com.example.demoo.aop;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGlibMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public CGlibMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("-------------------- Interceptor --------------------------");

        if ("hey".equals(method.getName())) {
            return "크크크크";
        }
        return method.invoke(target, ((String) objects[0]).toLowerCase() );
    }
}
