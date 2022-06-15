package br.pucrs.adapter.service;


import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private VendaRepository repository;

    @Autowired
    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public Venda save(Venda venda) {
        return this.repository.save(venda);
    }

    public List<Venda> findAll() {
        return this.repository.findAll();
    }
}
