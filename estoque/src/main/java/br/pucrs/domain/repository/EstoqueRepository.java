package br.pucrs.domain.repository;

import br.pucrs.domain.entity.ItemEstoque;

import java.util.List;

public interface EstoqueRepository {

    ItemEstoque save(ItemEstoque model);

    List<ItemEstoque> findAll();

    ItemEstoque findOneByCodigoProduto(Integer codigoProduto);

}
