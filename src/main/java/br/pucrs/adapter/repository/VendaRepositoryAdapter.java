package br.pucrs.adapter.repository;

import br.pucrs.application.repository.VendaSpringRepository;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendaRepositoryAdapter implements VendaRepository {

    @Autowired
    private VendaSpringRepository repository;

    @Override
    public Venda save(Venda model) {
        return this.repository.save(model);
    }
}
