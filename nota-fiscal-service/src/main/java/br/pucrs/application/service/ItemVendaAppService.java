package br.pucrs.application.service;

import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.repository.ItemVendaRepository;
import br.pucrs.domain.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemVendaAppService implements ItemVendaService {
    private ItemVendaRepository repository;

    @Autowired
    public ItemVendaAppService(ItemVendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemVenda save(ItemVenda itemVenda) {
        return this.repository.save(itemVenda);
    }
}
