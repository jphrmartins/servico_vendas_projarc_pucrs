package br.pucrs.application.calculator.limit;

import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.application.exception.LimitExceedOnSaleException;

import java.util.List;

public class MaxSale5LimitSaleLimiter implements SaleLimiter {
    private static final Integer MAX_SALE_QUANTITY = 5;

    @Override
    public void canProcessSale(List<Integer> quantidades) {
        boolean canProcess = quantidades.stream()
                .allMatch(it -> it <= MAX_SALE_QUANTITY);
        if (!canProcess) {
            throw new LimitExceedOnSaleException("Ultrapassou o limite de " + MAX_SALE_QUANTITY + " em algum item");
        }
    }
}
