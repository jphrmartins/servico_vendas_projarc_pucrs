package br.pucrs.application.repository;

import br.pucrs.domain.entity.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoSpringRepository extends CrudRepository<Produto, Integer> {
}
