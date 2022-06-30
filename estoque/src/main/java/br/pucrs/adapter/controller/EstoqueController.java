package br.pucrs.adapter.controller;

import br.pucrs.adapter.dto.SaleQuantityCheckRequest;
import br.pucrs.application.exception.LimitExceedOnSaleException;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Boolean> checkAvailability(@RequestParam("codProd") int productCode, @RequestParam("qtdade") int quantity) {
        try {
            return ResponseEntity.ok(this.service.checkAvailability(productCode, quantity));
        } catch (LimitExceedOnSaleException ex) {
            return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/validar-quantidade")
    public ResponseEntity<Boolean> checkSaleAvailability(@RequestBody SaleQuantityCheckRequest quantityCheckRequest) {
        try {
            return ResponseEntity.ok(this.service.checkAvailabilityForSale(quantityCheckRequest.getQuantities()));
        } catch (LimitExceedOnSaleException ex) {
            return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoId(@PathVariable("id") int produtoId) {
        return service.getProdutoById(produtoId);
    }
}
