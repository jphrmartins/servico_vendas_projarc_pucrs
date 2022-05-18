package br.pucrs.application.calculator;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.constants.CostType;

public interface CostCalculator {
    boolean canCalculate(CostType costType);

    double calculate(VendaDTO venda);
}
