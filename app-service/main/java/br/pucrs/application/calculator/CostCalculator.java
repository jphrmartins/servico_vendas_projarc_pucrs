package br.pucrs.application.calculator;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.entity.ItemVenda;

import java.util.List;

public interface CostCalculator {
    boolean canCalculate(CostType costType);

    double calculate(VendaDTO venda, List<ItemVenda> itens);
}
