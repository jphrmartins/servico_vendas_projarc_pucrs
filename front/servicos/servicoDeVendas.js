class ServicoDeVendas {
  async autoriza(codigo, quantidade) {
    let url = this.baseUrl + "/estoque/validar-quantidade";
    url += "?codProd=" + codigo + "&qtdade=" + quantidade;

    try {
      let resposta = await fetch(url);
      if (resposta.ok) {
        let aprovacao = await resposta.json();
        return aprovacao;
      } else {
        console.error(resposta.status, resposta.statusText);
      }
    } catch (erro) {
      console.error(erro);
    }
    return false;
  }

  async frete(endereco) {
    let url = this.baseUrl + "/vendas/frete";
    url += "?endereco=" + encodeURIComponent(endereco);

    try {
      let resposta = await fetch(url);
      if (resposta.ok) {
        let aprovacao = await resposta.json();
        return aprovacao;
      } else {
        console.error(resposta.status, resposta.statusText);
      }
    } catch (erro) {
      console.error(erro);
    }
    return null;
  }

  async calculaSubtotal(itens, endereco) {
    const url = this.baseUrl + "/vendas/simular";
    const lstItens = [];

    itens.forEach((item) => {
      lstItens.push({ codigo: item.produto.codigo, quantidade: item.qtdade });
    });

    const param = { itens: lstItens, endereco: endereco };

    const otherParam = {
      headers: { "content-type": "application/json" },
      body: JSON.stringify(param),
      method: "POST",
    };

    try {
      let resposta = await fetch(url, otherParam);
      if (resposta.ok) {
        let totais = await resposta.json();
        return totais;
      } else {
        const erro = await resposta.json();
        return { erro: true, ...erro };
      }
    } catch (erro) {
      console.log(erro);
    }
  }

  async confirmaVenda(itens, endereco) {
    const url = this.baseUrl + "/vendas/confirmacao";
    const param = [];

    itens.forEach((item) => {
      param.push({ codigo: item.produto.codigo, quantidade: item.qtdade });
    });

    const otherParam = {
      headers: { "content-type": "application/json" },
      body: JSON.stringify({ itens: param, endereco }),
      method: "POST",
    };

    try {
      let resposta = await fetch(url, otherParam);
      if (resposta.ok) {
        let resp = await resposta.json();
        return resp;
      }
    } catch (erro) {
      console.log(erro);
    }
    return false;
  }

  async getProdutos() {
    const url = this.baseUrl + "/estoque/produtos-disponiveis";
    const produtos = [];

    try {
      let resposta = await fetch(url);
      if (resposta.ok) {
        let dados = await resposta.json();
        for (let i = 0; i < dados.length; i++) {
          produtos.push(
            new Produto(
              dados[i].codigo,
              dados[i].descricao,
              dados[i].preco,
              dados[i].qtdade
            )
          );
        }
      }
    } catch (erro) {
      console.log(erro);
    }

    return produtos;
  }

  constructor(baseUrl) {
    this.baseUrl = baseUrl;
  }
}
