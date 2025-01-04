package com.practice.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String AUDIT_LOGS_QUEUE = "audit_logs_queue";

    @Bean
    public Queue auditLogsQueue() {
        return new Queue(AUDIT_LOGS_QUEUE, true);
    }

    @Bean
    public Queue checkReservationQueue() {
        return new Queue("checkReservationQueue", false);
    }

    @Bean
    public TopicExchange exchangeCheck() {
        return new TopicExchange("reservationExchange", false, false);
    }

    @Bean
    public Binding checkReservationBinding(Queue checkReservationQueue, TopicExchange exchangeCheck) {
        return BindingBuilder.bind(checkReservationQueue).to(exchangeCheck).with("reservation.check.key");
    }

    @Bean
    public Queue responseQueue() {
        return new Queue("response.routing.key", false);
    }

    @Bean
    public TopicExchange reservationExchange() {
        return new TopicExchange("reservationExchange", false, false);
    }

    @Bean
    public Binding responseBinding(Queue responseQueue, TopicExchange reservationExchange) {
        return BindingBuilder.bind(responseQueue).to(reservationExchange).with("response.routing.key");
    }
}
