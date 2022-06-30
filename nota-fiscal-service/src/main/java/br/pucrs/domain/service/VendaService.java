package br.pucrs.domain.service;

import br.pucrs.domain.entity.Venda;

import java.util.List;

public interface VendaService {
    void save(Venda venda);

    List<Venda> findAll();

    void rollback(Integer vendaId);
}
