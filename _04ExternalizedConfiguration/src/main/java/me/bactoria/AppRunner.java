package me.bactoria;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

        @Value("${bactoria.name}") // bactoria.name 의 property key의 value를 매핑시킴.
        private String name;

        @Value("${bactoria.fullName}")
        private String fullName;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            System.out.println("===========");
            System.out.println("Runner " + name);
            System.out.println("===========");
        }
}

