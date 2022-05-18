package br.pucrs.domain.service;

import java.util.List;

import br.pucrs.adapter.dto.ItemVendaDTO;

public interface VendaService {
    void confirm(List<ItemVendaDTO> itens);
}
