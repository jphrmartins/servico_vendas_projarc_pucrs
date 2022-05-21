package br.pucrs.application.calculator.limit;

import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.application.exception.LimitExceedOnSaleException;
import br.pucrs.domain.entity.ItemVenda;

import java.util.List;

public class MaxSale15LimitSaleLimiter implements SaleLimiter {
    private static final Integer MAX_SALE_QUANTITY = 5;

    @Override
    public void canProcessSale(List<ItemVenda> items) {
        boolean canProcess = items.stream()
                .anyMatch(it -> it.getQuantidade() > MAX_SALE_QUANTITY);
        if (!canProcess) {
            throw new LimitExceedOnSaleException("Ultrapassou o limite de " + MAX_SALE_QUANTITY + " em algum item");
        }
    }
}
