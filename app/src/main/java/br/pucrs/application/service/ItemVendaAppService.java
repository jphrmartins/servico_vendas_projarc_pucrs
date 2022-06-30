package br.pucrs.application.service;

import br.pucrs.adapter.dto.ItemVendaDTO;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.service.EstoqueService;
import br.pucrs.domain.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemVendaAppService implements ItemVendaService {
    private EstoqueService estoqueService;

    @Autowired
    public ItemVendaAppService(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @Override
    public ItemVenda createModelFromDTO(ItemVendaDTO dto) {
        Produto produto = estoqueService.getProdutoId(dto.getCodigo());
        return new ItemVenda(produto, dto.getQuantidade(), produto.getPreco() * dto.getQuantidade());
    }
}
