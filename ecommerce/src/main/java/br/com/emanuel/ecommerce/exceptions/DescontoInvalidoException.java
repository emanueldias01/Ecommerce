package br.com.emanuel.ecommerce.exceptions;

public class DescontoInvalidoException extends RuntimeException{
    private final String lancamento = "desconto inválido";

    public DescontoInvalidoException(String message) {
        super(message);
    }

    public String getLancamento() {
        return lancamento;
    }
}
