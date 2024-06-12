package br.com.emanuel.ecommerce.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {

    private final String FROM = "emanuelaraujo90@gmail.com";
    private String to;
    private String subject;
    private String mensagem;

    public Email(String to){
        this.to = to;
    }
}
