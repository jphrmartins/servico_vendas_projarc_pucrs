package br.pucrs;

import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.EstoqueRepository;
import br.pucrs.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrapper {
    private ProdutoRepository produtoRepository;
    private EstoqueRepository estoqueRepository;

    @Autowired
    public Bootstrapper(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @PostConstruct
    public void initDatabase() {
        if (!this.produtoRepository.findAll().iterator().hasNext()) {
            this.addToEstoque(10, "Geladeira", 2500.0, 10);
            this.addToEstoque(20, "Fogao", 1200.0, 8);
            this.addToEstoque(30, "Lava louça", 4300.0, 5);
            this.addToEstoque(40, "Lava roupa", 3350.0, 3);
            this.addToEstoque(50, "Aspirador de pó", 780.0, 1);
        }
    }

    private void addToEstoque(int codigo, String name, double preco, int quantidade) {
        Produto model = this.produtoRepository.save(new Produto(codigo, name, preco));
        ItemEstoque item = new ItemEstoque(model, quantidade);
        estoqueRepository.save(item);
    }
}
