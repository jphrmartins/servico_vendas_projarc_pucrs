package br.pucrs.application.service;

import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ItemEstoqueRepository;
import br.pucrs.domain.service.ItemEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemEstoqueAppService implements ItemEstoqueService {
    private ItemEstoqueRepository repository;
    private SaleLimiter limiters;


    @Autowired
    public ItemEstoqueAppService(ItemEstoqueRepository repository, SaleLimiter limiters) {
        this.repository = repository;
        this.limiters = limiters;
    }

    public void updateQuantity(int code, int quantity) {
        ItemEstoque item = this.repository.findOneByCodigoProduto(code);
        item.setQuantidade(item.getQuantidade() - quantity);
        this.repository.save(item);
    }

    public List<Produto> findAvailableProducts() {
        return repository.findAll()
                .stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(ItemEstoque::getProduto)
                .collect(Collectors.toList());

    }

    public boolean checkAvailability(int productCode, int quantity) {
        ItemEstoque item = this.repository.findOneByCodigoProduto(productCode);
        if (item == null)
            return false;
        limiters.canProcessSale(Collections.singletonList(quantity));
        return item.getQuantidade() >= quantity;
    }
}
