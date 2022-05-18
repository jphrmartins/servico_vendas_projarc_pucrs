package br.pucrs.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ItemEstoqueRepository;
import br.pucrs.domain.service.ItemEstoqueService;

@Service
public class ItemEstoqueAppService implements ItemEstoqueService {

    @Autowired
    private ItemEstoqueRepository repository;

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
        return item.getQuantidade() >= quantity;
    }
}
