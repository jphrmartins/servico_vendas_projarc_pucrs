package br.pucrs.service;

import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemEstoqueService {

    @Autowired
    private ItemEstoqueRepository repository;

    public List<Produto> findAvailableProducts() {
        return repository.findAll()
                .stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(ItemEstoque::getProduto)
                .collect(Collectors.toList());

    }

    public boolean checkAvailability(int productCode, int quantity) {
        ItemEstoque item = this.repository.findOneByCodigoProduto(productCode);
        if (item == null) return false;
        return item.getQuantidade() >= quantity;
    }
}
