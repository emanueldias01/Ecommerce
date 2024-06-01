package br.com.emanuel.ecommerce.exceptions;

public class ProdutoExistException extends RuntimeException{
    private final String lancamento = "O produto já existe";

    public ProdutoExistException(String message) {
        super(message);
    }

    public String getLancamento() {
        return lancamento;
    }
}
