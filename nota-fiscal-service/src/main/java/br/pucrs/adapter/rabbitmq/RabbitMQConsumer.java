package br.pucrs.adapter.rabbitmq;

import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.service.VendaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    private VendaService vendaService;

    @RabbitListener(queues = {"Invoice"})
    public void receive(@Payload String body) {
        Venda venda = null;
        try {
            venda = new ObjectMapper().readValue(body, Venda.class);
            this.vendaService.save(venda);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("Message " + venda);
    }


}
