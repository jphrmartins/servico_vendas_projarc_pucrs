package br.pucrs.adapter.rabbitmq;

import br.pucrs.adapter.dto.ConfirmVenda;
import br.pucrs.domain.service.EstoqueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private EstoqueService estoqueService;

    @Autowired
    public RabbitMQConsumer(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @RabbitListener(queues = {"SaleConfirm"})
    public void receiveSale(@Payload String body) {
        ConfirmVenda venda = null;
        try {
            venda = new ObjectMapper().readValue(body, ConfirmVenda.class);
            estoqueService.validadeSale(venda);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("Message " + venda);
    }


}
