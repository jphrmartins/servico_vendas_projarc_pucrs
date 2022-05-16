package br.pucrs.controller;

import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private ItemEstoqueRepository repository;

    @GetMapping()
    public Iterable<ItemEstoque> available() {
        return repository.findAll();
    }
}
