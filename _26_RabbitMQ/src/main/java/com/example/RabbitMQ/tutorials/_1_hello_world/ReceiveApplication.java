package com.example.RabbitMQ.tutorials._1_hello_world;

class ReceiveApplication {

    public static void main(String[] args) throws Exception {
        ReceiveMessage receiveMessage = new ReceiveMessage();

        receiveMessage.startReceive();
    }
}
