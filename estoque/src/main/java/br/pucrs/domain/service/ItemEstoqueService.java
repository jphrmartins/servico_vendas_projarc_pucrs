package br.pucrs.domain.service;

import br.pucrs.domain.entity.Produto;

import java.util.List;

public interface ItemEstoqueService {
    void updateQuantity(int code, int quantity);

    List<Produto> findAvailableProducts();

    boolean checkAvailability(int productCode, int quantity);
}
