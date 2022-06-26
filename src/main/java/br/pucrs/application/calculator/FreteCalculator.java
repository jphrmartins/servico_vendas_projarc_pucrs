package br.pucrs.application.calculator;

import br.pucrs.application.service.AddressService;
import br.pucrs.domain.entity.ItemVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.application.constants.CostType;

import java.util.List;

@Component
public class FreteCalculator implements CostCalculator {

    @Autowired
    AddressService addressService;

    @Override
    public boolean canCalculate(CostType costType) {
        return costType == CostType.FRETE;
    }

    @Override
    public double calculate(VendaDTO venda, List<ItemVenda> itens) {
        return addressService.distanceFromStore(venda.getEndereco());
    }

}
