package br.pucrs.domain;

import javax.persistence.*;

@Entity
public class ItemEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Produto produto;
    private int quantidade;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ItemEstoque(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemEstoque() {
    }

    @Override
    public String toString() {
        return "ItemEstoque{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}