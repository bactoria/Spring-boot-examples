package com.example.RabbitMQ.provider_topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.RabbitMQ.common.Constant.*;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

/*
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory;
    }
*/

    @Bean
    public Binding binding_queue1_key1(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(QUEUE_ROUTING_KEY1);
    }

    @Bean
    public Binding binding_queue1_key2(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(QUEUE_ROUTING_KEY2);
    }

    @Bean
    public Binding binding_queue2_key2(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(QUEUE_ROUTING_KEY2);
    }

    @Bean
    public Binding binding_queue2_key3(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(QUEUE_ROUTING_KEY3);
    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
