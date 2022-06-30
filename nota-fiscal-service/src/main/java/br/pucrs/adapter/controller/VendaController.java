package br.pucrs.adapter.controller;

import br.pucrs.application.service.VendaAppService;
import br.pucrs.domain.entity.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private VendaAppService vendaService;

    @Autowired
    public VendaController(VendaAppService vendaService) {
        this.vendaService = vendaService;
    }


    @GetMapping()
    public List<Venda> buscarNFE() {
        try {
            return vendaService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
