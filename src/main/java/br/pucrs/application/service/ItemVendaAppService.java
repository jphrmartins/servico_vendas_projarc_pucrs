package br.pucrs.application.service;

import br.pucrs.adapter.dto.ItemVendaDTO;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ItemVendaRepository;
import br.pucrs.domain.repository.ProdutoRepository;
import br.pucrs.domain.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ItemVendaAppService implements ItemVendaService {

    @Autowired
    private ItemVendaRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public ItemVenda saveFromDTO(ItemVendaDTO dto) {
        Produto produto = produtoRepository.findById(dto.getCodigo());
        ItemVenda model = new ItemVenda(produto, dto.getQuantidade(), produto.getPreco() * dto.getQuantidade());
        return this.repository.save(model);
    }
}
