package com.practice.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String QUEUE_NAME = "bookReservationQueue";
    public static final String EXCHANGE_NAME = "testExchange";
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }
    @Bean
    Exchange exchange() {
        return new TopicExchange(EXCHANGE_NAME, false, false);
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("book.routing.key").noargs();
    }

}
