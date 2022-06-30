package br.pucrs;

import br.pucrs.rabbitmq.RabbitHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
    private RabbitHelper rabbitHelper;

    @Autowired
    public Initializer(RabbitHelper rabbitHelper) {
        this.rabbitHelper = rabbitHelper;
    }

    @PostConstruct
    public void initQueues() {
        this.rabbitHelper.createQueue("Invoice");
    }

}
