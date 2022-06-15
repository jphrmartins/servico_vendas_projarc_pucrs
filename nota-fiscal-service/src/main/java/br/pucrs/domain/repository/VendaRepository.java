package br.pucrs.domain.repository;

import br.pucrs.domain.entity.Venda;

import java.util.List;

public interface VendaRepository {
    Venda save(Venda model);

    List<Venda> findAll();
}
