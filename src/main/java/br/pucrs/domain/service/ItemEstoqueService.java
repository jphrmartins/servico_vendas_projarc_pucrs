package br.pucrs.domain.service;

import java.util.List;

import br.pucrs.domain.entity.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "estoque", url = "localhost:8100/estoque")
public interface ItemEstoqueService {

    @PostMapping("/quantity")
    void updateQuantity(int code, int quantity);

    @GetMapping("/produtos-disponiveis")
    List<Produto> findAvailableProducts();

    @GetMapping("/validar-quantidade")
    boolean checkAvailability(@Requesint productCode, int quantity);
}
