package br.pucrs.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucrs.adapter.dto.ItemVendaDTO;
import br.pucrs.domain.service.VendaService;

@Service
public class VendaAppService implements VendaService {

    @Autowired
    private ItemEstoqueAppService estoqueService;

    public void confirm(List<ItemVendaDTO> itens) {
        itens.forEach(item -> estoqueService.updateQuantity(item.getCodigo(), item.getQuantidade()));
    }

}
