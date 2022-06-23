package br.pucrs.application.service;


import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.VendaRepository;
import br.pucrs.domain.service.ItemVendaService;
import br.pucrs.domain.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaAppService implements VendaService {

    private VendaRepository repository;
    private ItemVendaService itemVendaService;

    @Autowired
    public VendaAppService(VendaRepository repository, ItemVendaService itemVendaService) {
        this.repository = repository;
        this.itemVendaService = itemVendaService;
    }

    public void save(Venda venda) {
        List<ItemVenda> models = venda.getItensVenda().stream().map(item -> this.itemVendaService.save(item)).collect(Collectors.toList());
        venda.setItensVenda(models);
        this.repository.save(venda);
    }

    public List<Venda> findAll() {
        return this.repository.findAll();
    }
}
