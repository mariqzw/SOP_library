package com.practice.demo.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SendController {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "bookExchange";
    public static final String ROUTING_KEY = "book.routing.key";

    public SendController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public ResponseEntity<String> sendMessageToQueue(@RequestBody String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        return ResponseEntity.ok("Message sent to RabbitMQ!");
    }
}
