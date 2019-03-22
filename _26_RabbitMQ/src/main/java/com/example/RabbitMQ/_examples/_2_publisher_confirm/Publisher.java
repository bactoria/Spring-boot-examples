package com.example.RabbitMQ._examples._2_publisher_confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

class Publisher {

    public static void main(String[] args) throws Exception {

        final String QUEUE_NAME = "publusher_confirm_queue";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) {
                System.out.println("ACK: " + deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                System.out.println("NACK: " + deliveryTag);
            }
        });

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        final int MESSAGE_COUNT = 100;
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            Thread.sleep(100);
            channel.basicPublish("", QUEUE_NAME, null, ("Hello, This is Message" + i).getBytes("UTF-8"));
        }
    }
}
