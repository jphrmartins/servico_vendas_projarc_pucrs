package br.pucrs.adapter.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.VendaRepository;

@Component
public class VendaRepositoryAdapter implements VendaRepository {

    @Autowired
    private VendaSpringRepository repository;

    @Override
    public Venda save(Venda model) {
        return this.repository.save(model);
    }

    @Override
    public List<Venda> findAll() {
        return Streamable.of(this.repository.findAll()).toList();
    }
}
