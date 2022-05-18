package br.pucrs.application.calculator;

import br.pucrs.domain.entity.ItemVenda;
import org.springframework.stereotype.Component;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.constants.CostType;

import java.util.List;

@Component
public class FreteCalculator implements CostCalculator {

    @Override
    public boolean canCalculate(CostType costType) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public double calculate(VendaDTO venda, List<ItemVenda> itens) {
        // TODO Auto-generated method stub
        return 0;
    }

}
