package br.pucrs.domain.repository;

import java.util.List;

import br.pucrs.domain.entity.Produto;

public interface ProdutoRepository {

    Produto save(Produto entity);

    Iterable<Produto> findAll();

    Produto findById(Integer id);

}
