package br.pucrs.adapter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.adapter.dto.ItemVendaDTO;
import br.pucrs.domain.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/confirmacao")
    public boolean confirm(@RequestBody List<ItemVendaDTO> itens) {
        try {
            vendaService.confirm(itens);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
