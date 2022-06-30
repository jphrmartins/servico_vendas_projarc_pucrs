package br.pucrs.application.service;

import br.pucrs.adapter.dto.ConfirmVenda;
import br.pucrs.adapter.rabbitmq.RabbitMQPublisher;
import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.application.exception.LimitExceedOnSaleException;
import br.pucrs.domain.entity.ItemEstoque;
import br.pucrs.domain.entity.Produto;
import br.pucrs.domain.repository.EstoqueRepository;
import br.pucrs.domain.repository.ProdutoRepository;
import br.pucrs.domain.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemEstoqueAppService implements EstoqueService {
    private EstoqueRepository estoqueRepository;
    private ProdutoRepository produtoRepository;
    private RabbitMQPublisher publisher;
    private SaleLimiter limiters;

    @Autowired
    public ItemEstoqueAppService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository,
                                 SaleLimiter limiters, RabbitMQPublisher publisher) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.limiters = limiters;
        this.publisher = publisher;
    }

    public List<Produto> findAvailableProducts() {
        return estoqueRepository.findAll()
                .stream()
                .filter(item -> item.getQuantidade() > 0)
                .map(ItemEstoque::getProduto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ItemEstoque> listAll() {
        return estoqueRepository.findAll();
    }

    public boolean checkAvailability(int productCode, int quantity) {
        ItemEstoque item = this.estoqueRepository.findOneByCodigoProduto(productCode);
        if (item == null)
            return false;
        limiters.canProcessSale(Collections.singletonList(quantity));
        return item.getQuantidade() >= quantity;
    }

    @Override
    public boolean checkAvailabilityForSale(List<Integer> quantities) {
        limiters.canProcessSale(quantities);
        return true;
    }

    @Override
    public Produto getProdutoById(int produtoId) {
        return produtoRepository.findById(produtoId);
    }

    @Override
    public void validadeSale(ConfirmVenda venda) {
        try {
            boolean isVendaValid = venda.getItensVenda().stream()
                    .allMatch(item -> checkAvailability(item.getId(), item.getQuantidade()));
            if (isVendaValid) {
                updateSaleQuantity(venda);
            } else {
                rollbackSale(venda.getId());
            }
        } catch (LimitExceedOnSaleException ex) {
            rollbackSale(venda.getId());
        }

    }

    private void rollbackSale(Integer vendaId) {
        publisher.send(vendaId);
    }

    private void updateSaleQuantity(ConfirmVenda venda) {
        venda.getItensVenda()
                .forEach(it -> updateQuantity(it.getId(), it.getQuantidade()));
    }

    private void updateQuantity(int code, int quantity) {
        ItemEstoque item = this.estoqueRepository.findOneByCodigoProduto(code);
        item.setQuantidade(item.getQuantidade() - quantity);
        this.estoqueRepository.save(item);
    }
}
