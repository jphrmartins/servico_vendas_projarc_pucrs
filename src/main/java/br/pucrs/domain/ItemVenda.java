package br.pucrs.domain;

public class ItemVenda {
    private Produto produto;
    private double precoVenda;
    private int quantidade;


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }


    public ItemVenda(Produto produto, double precoVenda, int quantidade) {
        this.produto = produto;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "produto=" + produto +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                '}';
    }
}