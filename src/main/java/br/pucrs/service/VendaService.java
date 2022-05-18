package br.pucrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucrs.dto.ItemVendaDTO;

@Service
public class VendaService {

    @Autowired
    private ItemEstoqueService estoqueService;

    public void confirm(List<ItemVendaDTO> itens) {
        itens.forEach(item -> estoqueService.updateQuantity(item.getCodigo(), item.getQuantidade()));
    }

}
