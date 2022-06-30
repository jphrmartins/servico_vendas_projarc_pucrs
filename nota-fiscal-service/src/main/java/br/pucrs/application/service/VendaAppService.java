package br.pucrs.application.service;


import br.pucrs.adapter.rabbitmq.RabbitMQPublisher;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.VendaRepository;
import br.pucrs.domain.service.ItemVendaService;
import br.pucrs.domain.service.VendaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaAppService implements VendaService {

    private RabbitMQPublisher publisher;
    private ObjectMapper mapper;
    private VendaRepository repository;
    private ItemVendaService itemVendaService;

    @Autowired
    public VendaAppService(ObjectMapper mapper, VendaRepository repository, ItemVendaService itemVendaService, RabbitMQPublisher publisher) {
        this.mapper = mapper;
        this.repository = repository;
        this.itemVendaService = itemVendaService;
        this.publisher = publisher;
    }

    public void save(Venda venda) {
        List<ItemVenda> models = venda.getItensVenda().stream().map(item -> this.itemVendaService.save(item)).collect(Collectors.toList());
        venda.setItensVenda(models);
        this.repository.save(venda);
        this.publisher.send(venda);
    }

    public List<Venda> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void rollback(Integer vendaId) {
        Optional<Venda> vendaOpc = this.repository.find(vendaId);
        if (vendaOpc.isPresent()) {
            Venda venda = vendaOpc.get();
            venda.setErrorOnProcess(true);
            this.repository.save(venda);
        } else {
            System.out.println("Could not found venda: " + vendaId);
        }
    }
}
