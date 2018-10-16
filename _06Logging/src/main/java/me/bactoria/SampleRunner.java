package me.bactoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);



    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 라고 log 찍힘!
        logger.debug("=============================");
        logger.debug("호우");
        logger.debug("=============================");


        System.out.println("========================");
        System.out.println("Runner");
        System.out.println("========================");


    }
}
