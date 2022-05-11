package br.pucrs.domain;

public class Venda {
    private String codigoProduto;
    private String descricao;
    private int quantidade;

    public Venda(String codigoProduto, String descricao, int quantidade) {
        this.codigoProduto = codigoProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
