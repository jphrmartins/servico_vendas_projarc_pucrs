package br.pucrs.adapter.dto;

import java.util.List;

public class SaleQuantityCheckRequest {
    private List<Integer> quantities;

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }
}
