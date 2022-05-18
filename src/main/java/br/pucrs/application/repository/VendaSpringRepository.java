package br.pucrs.application.repository;

import br.pucrs.domain.entity.Venda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaSpringRepository extends CrudRepository<Venda, Integer> {
}
