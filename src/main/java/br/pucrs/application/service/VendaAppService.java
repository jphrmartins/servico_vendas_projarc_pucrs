package br.pucrs.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.factory.VendaFactory;
import br.pucrs.domain.repository.VendaRepository;
import br.pucrs.domain.service.VendaService;

@Service
public class VendaAppService implements VendaService {

    @Autowired
    private ItemEstoqueAppService estoqueService;

    @Autowired
    private VendaFactory factory;

    @Autowired
    private VendaRepository repository;

    public void confirm(VendaDTO vendaDTO) {
        vendaDTO.getItens().forEach(item -> estoqueService.updateQuantity(item.getCodigo(), item.getQuantidade()));
        this.repository.save(this.factory.create(vendaDTO));
    }

}
