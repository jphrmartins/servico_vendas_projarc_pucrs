package br.pucrs.domain.service;

import java.util.List;

import br.pucrs.adapter.dto.SimulacaoVendaDTO;
import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.domain.entity.Venda;

public interface VendaService {
    void confirm(VendaDTO dto);

    SimulacaoVendaDTO simulate(VendaDTO dto);

    List<Venda> history();
}
