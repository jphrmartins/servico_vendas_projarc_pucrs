package br.pucrs.adapter.dto;

import br.pucrs.domain.entity.Venda;

public class SimulacaoVendaDTO {
    double subtotal, imposto, total, frete;

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }


    public void setImposto(double imposto) {
        this.imposto = imposto;
    }


    public void setTotal(double total) {
        this.total = total;
    }


    public void setFrete(double frete) {
        this.frete = frete;
    }

    static public SimulacaoVendaDTO fromVenda(Venda venda) {
        SimulacaoVendaDTO simulacao = new SimulacaoVendaDTO();
        simulacao.setFrete(venda.getCustoFrete());
        simulacao.setImposto(venda.getImpostos());
        simulacao.setSubtotal(venda.getCustos());
        simulacao.setTotal(venda.getTotalAPagar());
        return simulacao;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImposto() {
        return imposto;
    }

    public double getTotal() {
        return total;
    }

    public double getFrete() {
        return frete;
    }
}
