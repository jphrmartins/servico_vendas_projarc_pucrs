package br.pucrs.adapter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfirmVenda {
    private Integer id;
    private List<ItemVenda> itensVenda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    @Override
    public String toString() {
        return "ConfirmVenda{" +
                "id=" + id +
                ", itensVenda=" + itensVenda +
                '}';
    }
}
