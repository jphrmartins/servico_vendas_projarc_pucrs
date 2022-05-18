package br.pucrs.adapter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.adapter.dto.VendaDTO;
import br.pucrs.domain.entity.Venda;
import br.pucrs.domain.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/confirmacao")
    public boolean confirm(@RequestBody VendaDTO dto) {
        try {
            vendaService.confirm(dto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping()
    public List<Venda> history() {
        return this.vendaService.history();
    }

}
