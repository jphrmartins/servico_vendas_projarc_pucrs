package br.pucrs.application.exception;

public class InvalidAddressException extends RuntimeException {

    public InvalidAddressException(String message) {
        super(message);
    }
}