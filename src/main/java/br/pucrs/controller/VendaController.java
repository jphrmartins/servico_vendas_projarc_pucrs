package br.pucrs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.dto.ItemVendaDTO;
import br.pucrs.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/confirmacao")
    public void confirm(@RequestBody List<ItemVendaDTO> itens) {
        vendaService.confirm(itens);
    }

}
