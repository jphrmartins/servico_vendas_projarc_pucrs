package br.pucrs.domain.service;

import br.pucrs.adapter.dto.ConfirmVenda;
import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;

import java.util.List;

public interface EstoqueService {
    List<Produto> findAvailableProducts();

    boolean checkAvailability(int productCode, int quantity);

    boolean checkAvailabilityForSale(List<Integer> quantities);

    Produto getProdutoById(int produtoId);

    void validadeSale(ConfirmVenda venda);

    List<ItemEstoque> listAll();
}
