package br.pucrs.adapter;

import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.repository.ItemVendaRepository;
import br.pucrs.repository.ItemVendaSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemVendaRepositoryAdapter implements ItemVendaRepository {

    @Autowired
    private ItemVendaSpringRepository repository;

    @Override
    public ItemVenda save(ItemVenda model) {
        return this.repository.save(model);
    }
}
