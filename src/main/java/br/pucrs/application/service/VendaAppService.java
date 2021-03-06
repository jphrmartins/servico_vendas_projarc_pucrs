package br.pucrs.application.service;

import java.util.List;
import java.util.stream.Collectors;

import br.pucrs.adapter.dto.SimulacaoVendaDTO;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.repository.ProdutoRepository;
import br.pucrs.domain.service.ItemVendaService;
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
    private ItemEstoqueService estoqueService;
    private VendaFactory factory;
    private VendaRepository repository;
    private ItemVendaService itemVendaService;

    @Autowired
    public VendaAppService(ItemEstoqueService estoqueService, VendaFactory factory,
                           VendaRepository repository, ItemVendaService itemVendaService) {
        this.estoqueService = estoqueService;
        this.factory = factory;
        this.repository = repository;
        this.itemVendaService = itemVendaService;
    }

    public void confirm(VendaDTO vendaDTO) {
        vendaDTO.getItens().forEach(item -> estoqueService.updateQuantity(item.getCodigo(), item.getQuantidade()));
        List<ItemVenda> itens = vendaDTO.getItens().stream().map(item -> this.itemVendaService.saveFromDTO(item)).collect(Collectors.toList());
        this.repository.save(this.factory.create(vendaDTO, itens));
    }

    @Override
    public SimulacaoVendaDTO simulate(VendaDTO dto) {
        List<ItemVenda> itens = dto.getItens().stream().map(item -> this.itemVendaService.createModelFromDTO(item)).collect(Collectors.toList());
        Venda venda = this.factory.create(dto, itens);
        return SimulacaoVendaDTO.fromVenda(venda);
    }

    @Override
    public List<Venda> history() {
        return this.repository.findAll();
    }

}
