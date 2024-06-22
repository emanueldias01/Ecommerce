package br.com.emanuel.ecommerce.email;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails_autenticando")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAutenticando {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Column(name = "validacacao")
    private LocalDateTime validacao;
    private Integer numero;

    public EmailAutenticando(String email, Integer numero){
        this.email = email;
        this.numero = numero;
        this.validacao = LocalDateTime.now();
    }
}
