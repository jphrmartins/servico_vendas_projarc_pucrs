package br.pucrs.domain;

public class SubTotal {
    private int totalBruto;
    private int imposto;
    private int total;
    private int frete;

    public SubTotal(int totalBruto, int imposto, int total, int frete) {
        this.totalBruto = totalBruto;
        this.imposto = imposto;
        this.total = total;
        this.frete = frete;
    }

    public int getTotalBruto() {
        return totalBruto;
    }

    public int getImposto() {
        return imposto;
    }

    public int getTotal() {
        return total;
    }

    public int getFrete() {
        return frete;
    }
}
