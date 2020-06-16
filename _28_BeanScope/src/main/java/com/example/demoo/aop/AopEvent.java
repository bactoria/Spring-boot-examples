package com.example.demoo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopEvent {

    @Around("execution(* com.example.demoo.aop.SimpleEventService.*(..))")
    public Object asd(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before");
        Object retVal = pjp.proceed();
        System.out.println("after");
        return retVal;
    }

}
