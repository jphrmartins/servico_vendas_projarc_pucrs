package br.pucrs.application.calculator;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.constants.CostType;
import org.springframework.stereotype.Component;

@Component
public class DescontoCalculator implements CostCalculator{
    @Override
    public boolean canCalculate(CostType costType) {
        return CostType.DESCONTO.equals(costType);
    }

    @Override
    public double calculate(VendaDTO venda) {
        return 0;
    }
}
