package br.pucrs.application.calculator;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.entity.ItemVenda;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DescontoCalculator implements CostCalculator{
    @Override
    public boolean canCalculate(CostType costType) {
        return CostType.DESCONTO.equals(costType);
    }

    @Override
    public double calculate(VendaDTO venda, List<ItemVenda> itens) {
        return 0;
    }
}
