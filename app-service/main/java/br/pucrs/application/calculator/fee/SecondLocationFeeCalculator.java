package br.pucrs.application.calculator.fee;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.calculator.CostCalculator;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.entity.ItemVenda;

import java.util.List;

public class SecondLocationFeeCalculator implements CostCalculator {
    private static final double HIGH_FEE_PERCENTAGE = 0.10;
    private static final double LOW_FEE_PERCENTAGE = 0.20;

    @Override
    public boolean canCalculate(CostType costType) {
        return CostType.IMPOSTOS.equals(costType);
    }

    @Override
    public double calculate(VendaDTO venda, List<ItemVenda> itens) {
        double total = itens.stream().map(ItemVenda::getPrecoVenda).reduce(0.0, Double::sum);
        if (total > 800) {
            return total * HIGH_FEE_PERCENTAGE;
        }
        return total * LOW_FEE_PERCENTAGE;
    }
}
