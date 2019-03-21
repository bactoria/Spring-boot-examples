package com.example.RabbitMQ.common;

public class Constant {

    public static final String QUEUE1 = "queue1";
    public static final String QUEUE2 = "queue2";

    public static final String QUEUE_ROUTING_KEY1 = "queue.*";
    public static final String QUEUE_ROUTING_KEY2 = "*.hello";
    public static final String QUEUE_ROUTING_KEY3 = "lazy.#";

    public static final String TOPIC_EXCHANGE = "topic_exchange";
}
