package br.pucrs.application.service;

import br.pucrs.adapter.dto.ItemVendaDTO;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ProdutoRepository;
import br.pucrs.domain.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemVendaAppService implements ItemVendaService {
    private ProdutoRepository produtoRepository;

    @Autowired
    public ItemVendaAppService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ItemVenda createModelFromDTO(ItemVendaDTO dto) {
        Produto produto = produtoRepository.findById(dto.getCodigo());
        return new ItemVenda(produto, dto.getQuantidade(), produto.getPreco() * dto.getQuantidade());
    }
}
