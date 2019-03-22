package com.example.RabbitMQ._examples._1_hello_world;

import com.rabbitmq.client.*;

import java.io.IOException;

class ReceiveMessage {

    private Connection connection;
    private Channel channel;
    private Constant constant = new Constant();

    protected ReceiveMessage() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    protected void startReceive() throws Exception {
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {


                String message = null;

                try {
                    message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        channel.basicConsume(constant.QUEUE_NAME, false, consumer);
    }

    protected void stopConnect() throws Exception {
        channel.close();
        connection.close();
    }

}
