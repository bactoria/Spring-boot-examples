package com.example.RabbitMQ.provider;

import com.example.RabbitMQ.common.Constant;
import com.example.RabbitMQ.common.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.stream.LongStream;

@Slf4j
@Component
public class ProvideScheduler {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProvideScheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "0/3 * * * * *")
    public void onProvide() {
        log.info("Sending message... Start");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        LongStream.rangeClosed(1, 10000)
//                .parallel()
                .mapToObj(i -> Message.of(i, "password" + i))
                .forEach(message -> rabbitTemplate.convertAndSend(Constant.QUEUE_NAME, message));

        stopWatch.stop();
        log.info(stopWatch.toString());
        log.info("Sending message... End");
    }
}
