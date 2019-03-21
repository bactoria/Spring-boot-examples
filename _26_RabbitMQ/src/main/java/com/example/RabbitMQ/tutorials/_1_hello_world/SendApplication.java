package com.example.RabbitMQ.tutorials._1_hello_world;

import java.util.stream.IntStream;

class SendApplication {

    public static void main(String[] args) throws Exception {
        SendMessage sendMessage = new SendMessage();

        IntStream.rangeClosed(1, 10000).forEach(x ->
                sendMessage.sendMessage("Hello world" + x)
        );

        sendMessage.stopConnect();

    }

}
