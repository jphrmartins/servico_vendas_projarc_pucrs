package br.pucrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.pucrs.application.repository.ProdutoSpringRepository;

@RestController
@RequestMapping("/vendas")
public class Controller {
    //    private final List<Produto> produtos;
//    private final List<String> vendasEfetuadas;
//    private final Map<String, Integer> cacheFrete;

    private ProdutoSpringRepository produtoRepository;

    @Autowired
    public Controller(ProdutoSpringRepository produtoSpringRepository) {
        this.produtoRepository = produtoSpringRepository;
        // Cria cache frete
//        cacheFrete = new HashMap<>();
        // Cria e carrega o estoque

        // Cria a lista de vendas efetuadas
//        vendasEfetuadas = new ArrayList<>();
    }
//
//    @GetMapping("/produtos")
//    @CrossOrigin(origins = "*")
//    public List<Produto> listaProdutos() {
//        return produtos;
//    }
//
//    @GetMapping("/autorizacao")
//    @CrossOrigin(origins = "*")
//    public boolean podeVender(@RequestParam final Integer codProd,
//                              @RequestParam final Integer qtdade) {
//        final boolean disponivel =
//                produtos.stream().anyMatch(p -> p.getCodigo() == codProd && p.getQtdade() >= qtdade);
//        return disponivel;
//    }
//
//    @PostMapping("/confirmacao")
//    @CrossOrigin(origins = "*")
//    public boolean confirmaVenda(@RequestBody final ItemVenda[] itens) {
//
//        ArrayList<Produto> listaProdutos = new ArrayList<>();
//        ArrayList<Integer> listaQtdades = new ArrayList<>();
//
//        for (ItemVenda item : itens) {
//            final Produto produto =
//                    produtos.stream().filter(p -> p.getCodigo() == item.getCodigo()).findAny().orElse(null);
//
//            if (produto == null) {
//                return false;
//            }
//
//            listaQtdades.add(item.getQuantidade());
//            listaProdutos.add(produto);
//        }
//
//        StringBuilder builder = new StringBuilder();
//
//        for (int i = 0; i < listaProdutos.size(); i++) {
//            final Produto produto = listaProdutos.get(i);
//            final int qtdade = listaQtdades.get(i);
//            produto.saidaDeProduto(qtdade);
//
//            builder.append(produto.getCodigo());
//            builder.append(" ");
//            builder.append(produto.getDescricao());
//            builder.append(" ");
//            builder.append(qtdade);
//            builder.append("\n");
//        }
//
//        vendasEfetuadas.add(builder.toString());
//        return true;
//    }
//
//    @GetMapping("/historico")
//    @CrossOrigin(origins = "*")
//    public List<String> vendasEfetuadas() {
//        return vendasEfetuadas;
//    }
//
//    @PostMapping("/subtotal")
//    @CrossOrigin(origins = "*")
//    public Integer[] calculaSubtotal(@RequestBody final ParamSubtotal_DTO param) {
//        Integer subtotal = 0;
//        Integer imposto = 0;
//
//        System.out.println(param.getEndereco());
//
//        // Verifica se o endereço é invalido
//        if (param.getEndereco() == null ||
//                param.getEndereco().isEmpty() ||
//                param.getEndereco().isBlank()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereco invalido");
//        }
//        // Verifica se o endereço já está na cache
//        if (!cacheFrete.keySet().contains(param.getEndereco())) {
//            // Calcula o frete
//            cacheFrete.put(param.getEndereco(), 25);
//        }
//
//        for (final ItemVenda it : param.getItens()) {
//            // Procurar o produto pelo código
//            final Produto prod =
//                    produtos.stream().filter(p -> p.getCodigo() == it.getCodigo()).findAny().orElse(null);
//
//            if (prod != null) {
//                subtotal += (int) (prod.getPreco() * it.getQuantidade());
//            } else {
//                throw new IllegalArgumentException("Codigo invalido");
//            }
//        }
//        imposto = (int) (subtotal * 0.1);
//        final Integer[] resp = new Integer[4];
//        resp[0] = subtotal;
//        resp[1] = imposto;
//        resp[2] = subtotal + imposto;
//        resp[3] = cacheFrete.get(param.getEndereco()); // Frete: custo fixo por enquanto
//        return resp;
//    }
}
