package br.pucrs.repository;

import br.pucrs.domain.entity.Venda;
import org.springframework.data.repository.CrudRepository;

public interface VendaSpringRepository extends CrudRepository<Venda, Integer> {
}
