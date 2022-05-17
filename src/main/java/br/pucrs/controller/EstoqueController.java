package br.pucrs.controller;

import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private ItemEstoqueRepository repository;

    @GetMapping("/produtos-disponiveis")
    public List<Produto> available() {
        List<ItemEstoque> itens = repository.findAll();
        return itens.stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(ItemEstoque::getProduto)
                .collect(Collectors.toList());
    }
}
