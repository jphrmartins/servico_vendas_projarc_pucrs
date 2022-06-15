package br.pucrs.adapter.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.VendaRepository;

@Component
public class VendaRepositoryAdapter implements VendaRepository {

    private VendaSpringRepository repository;

    @Autowired
    public VendaRepositoryAdapter(VendaSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public Venda save(Venda model) {
        return this.repository.save(model);
    }

    @Override
    public List<Venda> findAll() {
        return Streamable.of(this.repository.findAll()).toList();
    }
}
