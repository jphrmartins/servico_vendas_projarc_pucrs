package br.pucrs.adapter.dto;

import java.util.List;

public class VendaDTO {

    private List<ItemVendaDTO> itens;
    String endereco;

    public String getEndereco() {
        return endereco;
    }

    public List<ItemVendaDTO> getItens() {
        return itens;
    }

}
