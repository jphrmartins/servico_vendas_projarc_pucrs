class TotalView {
  definirValores(subtotal, imposto, total, frete) {
    this.subtotal.textContent = subtotal.toFixed(2);
    this.imposto.textContent = imposto.toFixed(2);
    this.frete.textContent = frete.toFixed(2);
    this.total.textContent = total.toFixed(2);
  }

  limpar() {
    this.subtotal.textContent = this.zero;
    this.imposto.textContent = this.zero;
    this.frete.textContent = this.zero;
    this.total.textContent = this.zero;
  }

  constructor(root) {
    this.subtotal = root.querySelector("#txtSubtotal label");
    this.imposto = root.querySelector("#txtImposto label");
    this.frete = root.querySelector("#txtFrete label");
    this.total = root.querySelector("#txtTotal label");
    this.zero = (0).toFixed(2);

    this.limpar();
  }
}
