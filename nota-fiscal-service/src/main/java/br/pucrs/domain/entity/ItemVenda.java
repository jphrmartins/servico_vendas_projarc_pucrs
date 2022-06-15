package br.pucrs.domain.entity;

import javax.persistence.*;

@Entity
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int produtoCodigo;
    private double precoVenda;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public ItemVenda(int quantidade, double precoVenda) {
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    public ItemVenda() {
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                '}';
    }
}
