package br.pucrs.adapter.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper mapper;

    private Queue queue;

    @Autowired
    public RabbitMQPublisher(RabbitTemplate rabbitTemplate, ObjectMapper mapper, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
        this.queue = queue;
    }

    public void send(Object order) {
        try {
            rabbitTemplate.convertAndSend(this.queue.getName(), mapper.writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}