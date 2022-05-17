package br.pucrs.adapter;

import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.repository.ItemEstoqueRepository;
import br.pucrs.repository.ItemEstoqueSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemEstoqueRepositoryAdapter implements ItemEstoqueRepository {
    @Autowired
    private ItemEstoqueSpringRepository repository;

    @Override
    public ItemEstoque save(ItemEstoque model) {
        return this.repository.save(model);
    }
}
