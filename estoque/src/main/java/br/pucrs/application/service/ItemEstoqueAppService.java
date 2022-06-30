package br.pucrs.application.service;

import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.EstoqueRepository;
import br.pucrs.domain.repository.ProdutoRepository;
import br.pucrs.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemEstoqueAppService implements EstoqueService {
    private EstoqueRepository estoqueRepository;
    private ProdutoRepository produtoRepository;
    private SaleLimiter limiters;

    @Autowired
    public ItemEstoqueAppService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository, SaleLimiter limiters) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.limiters = limiters;
    }

    public void updateQuantity(int code, int quantity) {
        ItemEstoque item = this.estoqueRepository.findOneByCodigoProduto(code);
        item.setQuantidade(item.getQuantidade() - quantity);
        this.estoqueRepository.save(item);
    }

    public List<Produto> findAvailableProducts() {
        return estoqueRepository.findAll()
                .stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(ItemEstoque::getProduto)
                .collect(Collectors.toList());

    }

    public boolean checkAvailability(int productCode, int quantity) {
        ItemEstoque item = this.estoqueRepository.findOneByCodigoProduto(productCode);
        if (item == null)
            return false;
        limiters.canProcessSale(Collections.singletonList(quantity));
        return item.getQuantidade() >= quantity;
    }

    @Override
    public boolean checkAvailabilityForSale(List<Integer> quantities) {
        limiters.canProcessSale(quantities);
        return true;
    }

    @Override
    public Produto getProdutoById(int produtoId) {
        return produtoRepository.findById(produtoId);
    }
}
