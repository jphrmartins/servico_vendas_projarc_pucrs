package br.pucrs.adapter.controller;

import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private EstoqueService service;

    @Autowired
    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping("/produtos-disponiveis")
    public List<Produto> available() {
        return this.service.findAvailableProducts();
    }

    @GetMapping("/validar-quantidade")
    public boolean checkAvailability(@RequestParam("codProd") int[] productsCode, @RequestParam("qtdade") int[] quantities) {
        return this.service.checkAvailability(productsCode, quantities);
    }
}
