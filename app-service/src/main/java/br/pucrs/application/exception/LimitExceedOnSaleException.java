package br.pucrs.application.exception;

public class LimitExceedOnSaleException extends RuntimeException {

    public LimitExceedOnSaleException(String message) {
        super(message);
    }
}
