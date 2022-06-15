package br.pucrs.adapter.repository;

import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.repository.ItemVendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemVendaRepositoryAdapter implements ItemVendaRepository {
    private ItemVendaSpringRepository repository;

    @Autowired
    public ItemVendaRepositoryAdapter(ItemVendaSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemVenda save(ItemVenda model) {
        return this.repository.save(model);
    }
}
