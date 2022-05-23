package br.pucrs.application.calculator;

import br.pucrs.application.exception.LimitExceedOnSaleException;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Venda;

import java.util.List;

public interface SaleLimiter {
    void canProcessSale(List<Integer> quantidade) throws LimitExceedOnSaleException;
}
