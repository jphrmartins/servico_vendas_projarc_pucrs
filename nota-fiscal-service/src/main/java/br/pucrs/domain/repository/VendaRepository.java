package br.pucrs.domain.repository;

import br.pucrs.domain.entity.Venda;

import java.util.List;
import java.util.Optional;

public interface VendaRepository {
    Venda save(Venda model);

    List<Venda> findAll();

    Optional<Venda> find(Integer vendaId);
}
