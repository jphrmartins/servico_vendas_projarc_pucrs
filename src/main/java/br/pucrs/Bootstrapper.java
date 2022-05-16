package br.pucrs;

import br.pucrs.domain.Produto;
import br.pucrs.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrapper {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostConstruct
    public void initDatabase() {
        if (!this.produtoRepository.findAll().iterator().hasNext()) {
            this.produtoRepository.save(new Produto(10, "Geladeira", 2500.0));
            this.produtoRepository.save(new Produto(20, "Fogao", 1200.0));
            this.produtoRepository.save(new Produto(30, "Lava louça", 4300.0));
            this.produtoRepository.save(new Produto(40, "Lava roupa", 3350.0));
            this.produtoRepository.save(new Produto(50, "Aspirador de pó", 780.0));
        }
    }

}
