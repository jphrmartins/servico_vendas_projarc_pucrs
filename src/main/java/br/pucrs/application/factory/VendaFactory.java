package br.pucrs.application.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.calculator.CostCalculator;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Venda;

@Component
public class VendaFactory {

    private SaleLimiter limiters;
    private List<CostCalculator> calculators;

    @Autowired
    public VendaFactory(SaleLimiter limiters, List<CostCalculator> calculators) {
        this.limiters = limiters;
        this.calculators = calculators;
    }

    public Venda create(VendaDTO dto, List<ItemVenda> itens) {
        limiters.canProcessSale(itens.stream().map(ItemVenda::getQuantidade).collect(Collectors.toList()));
        Venda venda = new Venda();
        this.setAllCosts(dto, venda, itens);
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
