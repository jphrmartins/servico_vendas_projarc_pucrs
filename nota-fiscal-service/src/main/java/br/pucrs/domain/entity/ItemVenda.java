package br.pucrs.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Map;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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

    public int getProdutoCodigo() {
        return produtoCodigo;
    }

    public void setProdutoCodigo(int produtoCodigo) {
        this.produtoCodigo = produtoCodigo;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "produtoCodigo = " + produtoCodigo +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                '}';
    }

    @JsonProperty("produto")
    public void extractId(Map<String, Object> produto) {
        this.produtoCodigo = (int) produto.get("codigo");
    }
}
