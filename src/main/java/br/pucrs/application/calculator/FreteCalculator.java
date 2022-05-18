package br.pucrs.application.calculator;

import org.springframework.stereotype.Component;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.constants.CostType;

@Component
public class FreteCalculator implements CostCalculator {

    @Override
    public boolean canCalculate(CostType costType) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double calculate(VendaDTO venda) {
        // TODO Auto-generated method stub
        return 0;
    }

}
