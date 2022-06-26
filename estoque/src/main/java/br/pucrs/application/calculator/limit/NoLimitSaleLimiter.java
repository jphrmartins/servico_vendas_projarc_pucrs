package br.pucrs.application.calculator.limit;

import br.pucrs.application.calculator.SaleLimiter;

import java.util.List;

public class NoLimitSaleLimiter implements SaleLimiter {
    @Override
    public void canProcessSale(List<Integer> quantidade) {
    }
}
