package br.pucrs.application.calculator.limit;

import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.domain.entity.ItemVenda;

import java.util.List;

public class NoLimitSaleLimiter implements SaleLimiter {
    @Override
    public void canProcessSale(List<ItemVenda> items) {
    }
}
