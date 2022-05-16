package br.pucrs.adapter;

import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ProdutoRepository;
import br.pucrs.repository.ProdutoSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepository {
    @Autowired
    private ProdutoSpringRepository repository;

    @Override
    public Produto save(Produto entity) {
        return this.repository.save(entity);
    }

    @Override
    public Iterable<Produto> findAll() {
        return this.repository.findAll();
    }
}
