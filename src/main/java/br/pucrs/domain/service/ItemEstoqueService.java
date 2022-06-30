package br.pucrs.domain.service;

import java.util.List;

import br.pucrs.domain.data.UpdateQuantityRequest;
import br.pucrs.domain.entity.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoque", url = "localhost:8100/estoque")
public interface ItemEstoqueService {
    @GetMapping("/produtos-disponiveis")
    List<Produto> findAvailableProducts();

    @GetMapping("/validar-quantidade")
    boolean checkAvailability(@RequestParam("codProd") int productCode, @RequestParam("qtdade") int quantity);
}
