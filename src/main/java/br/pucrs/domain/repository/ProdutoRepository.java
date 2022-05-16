package br.pucrs.domain.repository;

import br.pucrs.domain.entity.Produto;

public interface ProdutoRepository {

    Produto save(Produto entity);

    Iterable<Produto> findAll();

}
