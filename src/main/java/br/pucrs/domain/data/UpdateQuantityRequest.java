package br.pucrs.domain.data;

public class UpdateQuantityRequest {
    private int productCode;
    private int quantity;

    public UpdateQuantityRequest(int productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public UpdateQuantityRequest() {
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}