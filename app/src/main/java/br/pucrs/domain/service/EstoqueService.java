package br.pucrs.domain.service;

import br.pucrs.domain.data.SaleQuantityCheckRequest;
import br.pucrs.domain.entity.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "estoque", url = "localhost:8100/estoque")
public interface EstoqueService {
    @GetMapping("/produtos-disponiveis")
    List<Produto> findAvailableProducts();

    @GetMapping("/validar-quantidade")
    boolean checkAvailability(@RequestParam("codProd") int[] productCode, @RequestParam("qtdade") int[] quantity);

    @PostMapping("/validar-quantidade")
    void checkSaleAvailability(@RequestBody SaleQuantityCheckRequest request);

    @GetMapping("/produto/{id}")
    Produto getProdutoId(@PathVariable("id") int produtoId);
}
