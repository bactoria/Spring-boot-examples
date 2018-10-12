package me.bactoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner2 implements ApplicationRunner {

    @Autowired
    private BactoriaProperties bactoriaProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===========");
        System.out.println("Name : " + bactoriaProperties.getName());
        System.out.println("Age : " + bactoriaProperties.getAge());
        System.out.println("Full Name : " + bactoriaProperties.getFullName());
        System.out.println("Duration : " + bactoriaProperties.getSessionTimeout());
        System.out.println("===========");

    }
}
