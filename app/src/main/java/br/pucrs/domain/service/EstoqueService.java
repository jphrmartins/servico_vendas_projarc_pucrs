package br.pucrs.domain.service;

import br.pucrs.domain.data.SaleQuantityCheckRequest;
import br.pucrs.domain.entity.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "estoque")
public interface EstoqueService {
    @GetMapping("/estoque/produtos-disponiveis")
    List<Produto> findAvailableProducts();

    @GetMapping("/estoque/validar-quantidade")
    boolean checkAvailability(@RequestParam("codProd") int[] productCode, @RequestParam("qtdade") int[] quantity);

    @PostMapping("/estoque/validar-quantidade")
    void checkSaleAvailability(@RequestBody SaleQuantityCheckRequest request);

    @GetMapping("/estoque/produto/{id}")
    Produto getProdutoId(@PathVariable("id") int produtoId);
}
