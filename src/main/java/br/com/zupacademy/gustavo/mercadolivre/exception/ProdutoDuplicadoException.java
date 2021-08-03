package br.com.zupacademy.gustavo.mercadolivre.exception;

public class ProdutoDuplicadoException extends Exception{

    private String message;

    public ProdutoDuplicadoException() {
    }

    public ProdutoDuplicadoException(String message) {
        super(message);
    }
}
