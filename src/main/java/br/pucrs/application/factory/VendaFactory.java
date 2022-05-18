package br.pucrs.application.factory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.pucrs.adapter.dto.ItemVendaDTO;
import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.calculator.CostCalculator;
import br.pucrs.application.constants.CostType;
import br.pucrs.domain.entity.ItemVenda;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.repository.ProdutoRepository;

@Component
public class VendaFactory {

    @Autowired
    private List<CostCalculator> calculators;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda create(VendaDTO dto) {
        Venda venda = new Venda();
        this.setAllCosts(dto, venda);
        this.setItems(dto, venda);
        venda.setEndereco(dto.getEndereco());
        return venda;
    }

    private void setItems(VendaDTO dto, Venda venda) {
        List<Integer> productIds = dto.getItens().stream().map(ItemVendaDTO::getCodigo).collect(Collectors.toList());
        Map<Integer, List<Produto>> products = produtoRepository.findAllById(productIds).stream()
                .collect(Collectors.groupingBy(Produto::getCodigo));
        List<ItemVenda> itensVenda = dto.getItens().stream().map(item -> this.fromItemDTO(item, products))
                .collect(Collectors.toList());
        venda.setItensVenda(itensVenda);
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

    private ItemVenda fromItemDTO(ItemVendaDTO item, Map<Integer, List<Produto>> products) {
        Produto product = products.get(item.getCodigo()).get(0);
        return new ItemVenda(product, item.getQuantidade(), product.getPreco());
    }

}
