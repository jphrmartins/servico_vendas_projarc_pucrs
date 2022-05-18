package br.pucrs.adapter.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ProdutoRepository;

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

    @Override
    public Produto findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }
}
