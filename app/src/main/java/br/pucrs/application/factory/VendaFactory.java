package br.pucrs.application.factory;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.calculator.CostCalculator;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.data.SaleQuantityCheckRequest;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendaFactory {

    private EstoqueService estoqueService;
    private List<CostCalculator> calculators;

    @Autowired
    public VendaFactory(EstoqueService estoqueService, List<CostCalculator> calculators) {
        this.estoqueService = estoqueService;
        this.calculators = calculators;
    }

    public Venda create(VendaDTO dto, List<ItemVenda> itens) {
        List<Integer> quantities = itens.stream().map(ItemVenda::getQuantidade).collect(Collectors.toList());
        estoqueService.checkSaleAvailability(new SaleQuantityCheckRequest(quantities));
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
