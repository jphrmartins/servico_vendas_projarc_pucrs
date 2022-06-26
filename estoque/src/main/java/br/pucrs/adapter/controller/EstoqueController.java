package br.pucrs.adapter.controller;

import br.pucrs.adapter.dto.UpdateQuantityRequest;
import br.pucrs.application.exception.LimitExceedOnSaleException;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.service.ItemEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private ItemEstoqueService service;

    @Autowired
    public EstoqueController(ItemEstoqueService service) {
        this.service = service;
    }

    @PostMapping("/quantity")
    public ResponseEntity<Boolean> updateQuantity(@RequestBody UpdateQuantityRequest updateQuantityRequest) {
        this.service.updateQuantity(updateQuantityRequest.getProductCode(), updateQuantityRequest.getQuantity());
        return ResponseEntity.ok(true);
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
}
