package br.pucrs.application.factory;

import java.util.Arrays;
import java.util.Collections;
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
        this.setAllCosts(dto, venda);
        this.setSubtotal(venda, itens);
        venda.setTotalAPagar((venda.getCustos() - venda.getDescontos()) + venda.getCustoFrete());
        venda.setItensVenda(itens);
        venda.setEndereco(dto.getEndereco());
        return venda;
    }

    private void setSubtotal(Venda venda, List<ItemVenda> itens) {
        Double sum = itens.stream().map(ItemVenda::getPrecoVenda).reduce(0.0, Double::sum);
        venda.setCustos(sum);
    }

    private void setAllCosts(VendaDTO dto, Venda venda) {
        venda.setCustoFrete(this.calculate(CostType.FRETE, dto));
        venda.setDescontos(this.calculate(CostType.DESCONTO, dto));
        venda.setImpostos(this.calculate(CostType.IMPOSTOS, dto));
    }

    private double calculate(CostType type, VendaDTO dto) {
        return this.calculators.stream()
                .filter(calculator -> calculator.canCalculate(type))
                .findFirst()
                .get()
                .calculate(dto);
    }

}
