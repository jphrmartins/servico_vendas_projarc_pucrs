package br.pucrs.adapter.controller;

import br.pucrs.adapter.service.VendaService;
import br.pucrs.domain.entity.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }


    @PostMapping()
    public boolean registrarNFE(@RequestBody Venda venda) {
        try {
            vendaService.save(venda);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
