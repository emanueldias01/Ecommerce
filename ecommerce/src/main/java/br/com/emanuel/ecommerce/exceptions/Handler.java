package br.com.emanuel.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO trataProdutoExist(ProdutoExistException ex){
        return new ErrorDTO(
                ex.getLancamento(),
                ex.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO trataDescontoInvalido(DescontoInvalidoException ex){
        return new ErrorDTO(
                ex.getLancamento(),
                ex.getMessage()
        );
    }
}
