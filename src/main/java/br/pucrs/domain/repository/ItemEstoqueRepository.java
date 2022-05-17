package br.pucrs.domain.repository;

import br.pucrs.domain.entity.ItemEstoque;

import java.util.List;

public interface ItemEstoqueRepository {

    ItemEstoque save(ItemEstoque model);

    List<ItemEstoque> findAll();
}
