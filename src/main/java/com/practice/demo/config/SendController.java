package com.practice.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo.services.dtos.ReservationDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send/message")
public class SendController {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private static final String EXCHANGE_NAME_1 = "bookExchange";
    public static final String ROUTING_KEY_1 = "book.reservation.key";

    public SendController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<String> sendMessageToQueue(@RequestBody String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME_1, ROUTING_KEY_1, message);
        return ResponseEntity.ok("Message sent to RabbitMQ!");
    }


    @PostMapping("/check")
    public ResponseEntity<String> checkReservationStatus(@RequestBody ReservationDto reservationDto) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(reservationDto);
        rabbitTemplate.convertAndSend("reservationExchange", "reservation.check.key", message);
        return ResponseEntity.ok("Check status request sent to RabbitMQ!");
    }
}
