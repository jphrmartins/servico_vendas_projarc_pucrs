package br.pucrs.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitHelper {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    public Queue createQueue(String queueName) {
        Queue queue = new Queue(queueName);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }


}
