package br.pucrs.adapter.repository;

import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstoqueRepositoryAdapter implements EstoqueRepository {
    private ItemEstoqueSpringRepository repository;

    @Autowired
    public EstoqueRepositoryAdapter(ItemEstoqueSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemEstoque save(ItemEstoque model) {
        return this.repository.save(model);
    }

    @Override
    public List<ItemEstoque> findAll() {
        return Streamable.of(this.repository.findAll()).toList();
    }

    @Override
    public ItemEstoque findOneByCodigoProduto(Integer codigoProduto) {
        Produto produto = new Produto(codigoProduto);
        return this.repository.findOneByProduto(produto);
    }
}
