package br.pucrs.domain.service;

import java.util.List;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.domain.entity.Venda;

public interface VendaService {
    void confirm(VendaDTO dto);

    List<Venda> history();
}
