package br.pucrs.domain.service;

import java.util.List;

import br.pucrs.domain.entity.Produto;

public interface ItemEstoqueService {
    void updateQuantity(int code, int quantity);

    List<Produto> findAvailableProducts();

    boolean checkAvailability(int productCode, int quantity);
}
