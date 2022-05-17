package br.pucrs.repository;

import br.pucrs.domain.entity.ItemEstoque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEstoqueSpringRepository extends CrudRepository<ItemEstoque, Integer> {
}
