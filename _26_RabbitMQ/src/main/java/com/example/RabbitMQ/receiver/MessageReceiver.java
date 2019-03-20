package com.example.RabbitMQ.receiver;

import com.example.RabbitMQ.common.Constant;
import com.example.RabbitMQ.common.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageReceiver {

    @RabbitListener(queues = Constant.QUEUE_NAME)
    public void onMessage(Message message) {
        log.info("Received < " + message.toString() + " >");
    }
}
