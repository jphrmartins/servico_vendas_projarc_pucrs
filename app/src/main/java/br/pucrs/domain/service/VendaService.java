package br.pucrs.domain.service;

import br.pucrs.adapter.dto.SimulacaoVendaDTO;
import br.pucrs.adapter.dto.VendaDTO;

public interface VendaService {
    void confirm(VendaDTO dto);

    SimulacaoVendaDTO simulate(VendaDTO dto);
}
