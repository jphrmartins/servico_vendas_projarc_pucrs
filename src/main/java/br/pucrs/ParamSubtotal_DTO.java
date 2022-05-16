package br.pucrs;

import br.pucrs.domain.entity.ItemVenda;

public class ParamSubtotal_DTO {
    private ItemVenda[] itens;
    private String endereco;

    public ParamSubtotal_DTO(ItemVenda[] itens, String endereco) {
        this.itens = itens;
        this.endereco = endereco;
    }

    public ItemVenda[] getItens() {
        return itens;
    }

    public void setItens(ItemVenda[] itens) {
        this.itens = itens;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }    
}
