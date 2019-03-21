package com.example.RabbitMQ.provider_topic;

import com.example.RabbitMQ.common.Message;
import com.example.RabbitMQ.utils.exception.FunctionWithException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.function.Function;
import java.util.stream.LongStream;

import static com.example.RabbitMQ.common.Constant.TOPIC_EXCHANGE;

@Slf4j
@Component
public class ProvideScheduler {

    private final RabbitTemplate rabbitTemplate;

    public final String QUEUE_BYE = "queue.bye.q";
    public final String AA_HELLO = "aa.hello";
    public final String LAZY_HELLO = "lazy.hello";

    @Autowired
    public ProvideScheduler(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void onProvide() {
        log.info("Sending message... Start");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        LongStream.rangeClosed(1, 1000)
                .mapToObj(i -> Message.of(i, "password" + i))
                .forEach(message -> {
                    put_key1(message);
                    put_key2(message);
                    put_key3(message);
                });

        stopWatch.stop();
        log.info(stopWatch.toString());
        log.info("Sending message... End");
    }

    private <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    private void put_key1(Message message) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, QUEUE_BYE, message);
    }

    private void put_key2(Message message) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, AA_HELLO, message);
    }

    private void put_key3(Message message) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, LAZY_HELLO, message);
    }

}
