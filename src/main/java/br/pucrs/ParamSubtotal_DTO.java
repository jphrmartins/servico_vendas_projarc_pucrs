package br.pucrs;

import br.pucrs.domain.ItemCarrinho;

public class ParamSubtotal_DTO {
    private ItemCarrinho[] itens;
    private String endereco;

    public ParamSubtotal_DTO(ItemCarrinho[] itens, String endereco) {
        this.itens = itens;
        this.endereco = endereco;
    }

    public ItemCarrinho[] getItens() {
        return itens;
    }

    public void setItens(ItemCarrinho[] itens) {
        this.itens = itens;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }    
}
