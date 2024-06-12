package br.com.emanuel.ecommerce.exceptions;

public class UsuarioJaExisteException extends RuntimeException{

    private final String lancamento = "O usuario já existe";

    public String getLancamento() {
        return lancamento;
    }

    public UsuarioJaExisteException(String message) {
        super(message);
    }
}
