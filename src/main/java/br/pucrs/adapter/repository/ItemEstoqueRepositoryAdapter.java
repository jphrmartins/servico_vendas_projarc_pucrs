package br.pucrs.adapter.repository;

import br.pucrs.application.repository.ItemEstoqueSpringRepository;
import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ItemEstoqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ItemEstoqueRepositoryAdapter implements ItemEstoqueRepository {
    @Autowired
    private ItemEstoqueSpringRepository repository;

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
