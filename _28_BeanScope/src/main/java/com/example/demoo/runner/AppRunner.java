package com.example.demoo.runner;

import com.example.demoo.aop.SimpleEventService;
import com.example.demoo.aop.ProxySimpleEventService;
import com.example.demoo.aop.IBean2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IBean2 iBean2;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (String name : applicationContext.getBeanNamesForType(SimpleEventService.class)) {
            System.out.println(name);
        }

        System.out.println("-------------------");
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println("-------------------");

        System.out.println(applicationContext.getBean("simpleEventService"));
        System.out.println(applicationContext.getBean("aopEvent"));

        System.out.println("run!!!!");
        iBean2.asd();

    }
}
