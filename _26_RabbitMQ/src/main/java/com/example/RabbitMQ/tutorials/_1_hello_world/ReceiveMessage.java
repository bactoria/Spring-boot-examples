package com.example.RabbitMQ.tutorials._1_hello_world;

import com.rabbitmq.client.*;
import java.io.IOException;

class ReceiveMessage {

    private Connection connection;
    private Channel channel;
    private Constant constant = new Constant();

    public ReceiveMessage() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public void startReceive() throws Exception {
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


                // true : unacked에 있는 것까지 처리 ,
                // false : unacked에 있는 것은 처리안함.
                try {
                    channel.basicAck(envelope.getDeliveryTag(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        // true : ack 무시 (에러떠도 큐에꺼 다 빠짐)
        // false : ack 보냄 (exception 시 unacked로 보냄)
        channel.basicConsume(constant.QUEUE_NAME, false, consumer);
    }


    /**
     * 断开连接
     *
     * @throws Exception
     */
    public void stopConnect() throws Exception {
        channel.close();
        connection.close();
    }


}
