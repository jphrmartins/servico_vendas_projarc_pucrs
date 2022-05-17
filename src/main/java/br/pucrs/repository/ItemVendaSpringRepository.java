package br.pucrs.repository;

import br.pucrs.domain.entity.ItemVenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaSpringRepository extends CrudRepository<ItemVenda, Integer> {
}
