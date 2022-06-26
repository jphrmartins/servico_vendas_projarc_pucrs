package br.pucrs.application.calculator;

import br.pucrs.application.exception.LimitExceedOnSaleException;

import java.util.List;

public interface SaleLimiter {
    void canProcessSale(List<Integer> quantidade) throws LimitExceedOnSaleException;
}
