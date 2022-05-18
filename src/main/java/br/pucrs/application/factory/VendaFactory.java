package br.pucrs.application.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.calculator.CostCalculator;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Venda;

@Component
public class VendaFactory {

    @Autowired
    private List<CostCalculator> calculators;

    public Venda create(VendaDTO dto, List<ItemVenda> itens) {
        Venda venda = new Venda();
        this.setAllCosts(dto, venda, itens);
        venda.setItensVenda(itens);
        venda.setEndereco(dto.getEndereco());
        return venda;
    }

    private void setAllCosts(VendaDTO dto, Venda venda, List<ItemVenda> itens) {
        venda.setCustoFrete(this.calculate(CostType.FRETE, dto, itens));
        venda.setDescontos(this.calculate(CostType.DESCONTO, dto, itens));
        venda.setImpostos(this.calculate(CostType.IMPOSTOS, dto, itens));
    }

    private double calculate(CostType type, VendaDTO dto, List<ItemVenda> itens) {
        return this.calculators.stream()
                .filter(calculator -> calculator.canCalculate(type))
                .map(calculator -> calculator.calculate(dto, itens))
                .reduce(0.0, Double::sum);
    }

}
