package br.pucrs.domain.service;

import br.pucrs.adapter.dto.ItemVendaDTO;
import br.pucrs.domain.entity.ItemVenda;

public interface ItemVendaService {
    ItemVenda saveFromDTO(ItemVendaDTO dto);
}
