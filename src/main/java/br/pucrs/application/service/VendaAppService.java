package br.pucrs.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.factory.VendaFactory;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.VendaRepository;
import br.pucrs.domain.service.ItemEstoqueService;
import br.pucrs.domain.service.VendaService;

@Service
public class VendaAppService implements VendaService {

    @Autowired
    private ItemEstoqueService estoqueService;

    @Autowired
    private VendaFactory factory;

    @Autowired
    private VendaRepository repository;

    public void confirm(VendaDTO vendaDTO) {
        vendaDTO.getItens().forEach(item -> estoqueService.updateQuantity(item.getCodigo(), item.getQuantidade()));
        this.repository.save(this.factory.create(vendaDTO));
    }

    @Override
    public List<Venda> history() {
        return this.repository.findAll();
    }

}
