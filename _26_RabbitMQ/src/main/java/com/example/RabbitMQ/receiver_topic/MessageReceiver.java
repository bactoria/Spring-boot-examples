package com.example.RabbitMQ.receiver_topic;

import com.example.RabbitMQ.common.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.RabbitMQ.common.Constant.QUEUE1;
import static com.example.RabbitMQ.common.Constant.QUEUE2;

@Slf4j
@Component
public class MessageReceiver {

    @RabbitListener(queues = QUEUE1)
    public void onMessage1(Message message) {
        log.info(QUEUE1 + " :: " + message);
    }


    @RabbitListener(queues = QUEUE2)
    public void onMessage2(Message message) {
        log.info(QUEUE2 + " :: " + message);
    }

}
