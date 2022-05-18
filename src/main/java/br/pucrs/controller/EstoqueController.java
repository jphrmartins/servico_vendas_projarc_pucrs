package br.pucrs.controller;

import br.pucrs.domain.entity.Produto;
import br.pucrs.service.ItemEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private ItemEstoqueService service;

    @GetMapping("/produtos-disponiveis")
    public List<Produto> available() {
        return this.service.findAvailableProducts();
    }

    @GetMapping("/validar-quantidade")
    public boolean checkAvailability(@RequestParam("codProd") int productCode, @RequestParam("qtdade") int quantity) {
        return this.service.checkAvailability(productCode, quantity);
    }
}
