package br.pucrs.domain.data;

import java.util.List;

public class SaleQuantityCheckRequest {
    private List<Integer> quantities;

    public SaleQuantityCheckRequest(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }
}
