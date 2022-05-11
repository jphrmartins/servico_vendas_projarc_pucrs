package br.pucrs;

import br.pucrs.domain.ItemCarrinho;
import br.pucrs.domain.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vendas")
public class Controller {
  private final List<Produto> produtos;
  private final List<String> vendasEfetuadas;
  private final Map<String,Integer> cacheFrete; 

  public Controller() {
    // Cria cache frete
    cacheFrete = new HashMap<>();
    // Cria e carrega o estoque
    produtos = new ArrayList<>();
    produtos.add(new Produto(10, "Geladeira", 2500.0, 10));
    produtos.add(new Produto(20, "Fogao", 1200.0, 0));
    produtos.add(new Produto(30, "Lava louça", 4300.0, 7));
    produtos.add(new Produto(40, "Lava roupa", 3350.0, 11));
    produtos.add(new Produto(50, "Aspirador de pó", 780.0, 22));

    // Cria a lista de vendas efetuadas
    vendasEfetuadas = new ArrayList<>();
  }

  @GetMapping("/produtos")
  @CrossOrigin(origins = "*")
  public List<Produto> listaProdutos() {
    return produtos;
  }

  @GetMapping("/autorizacao")
  @CrossOrigin(origins = "*")
  public boolean podeVender(@RequestParam final Integer codProd,
                            @RequestParam final Integer qtdade) {
    final boolean disponivel =
        produtos.stream().anyMatch(p -> p.getCodigo() == codProd && p.getQtdade() >= qtdade);
    return disponivel;
  }

  @PostMapping("/confirmacao")
  @CrossOrigin(origins = "*")
  public boolean confirmaVenda(@RequestBody final ItemCarrinho[] itens) {

    ArrayList<Produto> listaProdutos = new ArrayList<>();
    ArrayList<Integer> listaQtdades = new ArrayList<>();

    for (ItemCarrinho item : itens) {
      final Produto produto =
          produtos.stream().filter(p -> p.getCodigo() == item.getCodigo()).findAny().orElse(null);

      if (produto == null) {
        return false;
      }

      listaQtdades.add(item.getQuantidade());
      listaProdutos.add(produto);
    }

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < listaProdutos.size(); i++) {
      final Produto produto = listaProdutos.get(i);
      final int qtdade = listaQtdades.get(i);
      produto.saidaDeProduto(qtdade);

      builder.append(produto.getCodigo());
      builder.append(" ");
      builder.append(produto.getDescricao());
      builder.append(" ");
      builder.append(qtdade);
      builder.append("\n");
    }

    vendasEfetuadas.add(builder.toString());
    return true;
  }

  @GetMapping("/historico")
  @CrossOrigin(origins = "*")
  public List<String> vendasEfetuadas() {
    return vendasEfetuadas;
  }

  @PostMapping("/subtotal")
  @CrossOrigin(origins = "*")
  public Integer[] calculaSubtotal(@RequestBody final ParamSubtotal_DTO param) {
    Integer subtotal = 0;
    Integer imposto = 0;

    System.out.println(param.getEndereco());

    // Verifica se o endereço é invalido
    if (param.getEndereco() == null || 
        param.getEndereco().isEmpty() ||
        param.getEndereco().isBlank()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Endereco invalido");
    }
    // Verifica se o endereço já está na cache
    if (!cacheFrete.keySet().contains(param.getEndereco())){
      // Calcula o frete 
      cacheFrete.put(param.getEndereco(),25);
    }

    for (final ItemCarrinho it : param.getItens()) {
      // Procurar o produto pelo código
      final Produto prod =
          produtos.stream().filter(p -> p.getCodigo() == it.getCodigo()).findAny().orElse(null);

      if (prod != null) {
        subtotal += (int) (prod.getPreco() * it.getQuantidade());
      } else {
        throw new IllegalArgumentException("Codigo invalido");
      }
    }
    imposto = (int) (subtotal * 0.1);
    final Integer[] resp = new Integer[4];
    resp[0] = subtotal;
    resp[1] = imposto;
    resp[2] = subtotal + imposto;
    resp[3] = cacheFrete.get(param.getEndereco()); // Frete: custo fixo por enquanto
    return resp;
  }}
