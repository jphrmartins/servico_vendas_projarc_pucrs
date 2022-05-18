package br.pucrs.adapter.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import br.pucrs.application.repository.ProdutoSpringRepository;
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
    public List<Produto> findAllById(Iterable<Integer> ids) {
        return Streamable.of(this.repository.findAllById(ids)).toList();
    }
}
