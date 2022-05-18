package br.pucrs.domain.repository;

import java.util.List;

import br.pucrs.domain.entity.Venda;

public interface VendaRepository {
    Venda save(Venda model);

    List<Venda> findAll();
}
