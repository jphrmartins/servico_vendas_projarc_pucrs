package br.pucrs.application.calculator.fee;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.calculator.CostCalculator;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.entity.ItemVenda;

import java.util.List;

public class SimpleFeeCalculator implements CostCalculator {
    @Override
    public boolean canCalculate(CostType costType) {
        return CostType.IMPOSTOS.equals(costType);
    }

    @Override
    public double calculate(VendaDTO venda, List<ItemVenda> itens) {
        venda.getItens();
        return 0;
    }
}
