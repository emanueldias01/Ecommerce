package br.com.emanuel.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String descricao;

    private BigDecimal preco;

    private String imagem;

    private Double desconto;

    @Enumerated(EnumType.STRING)
    private Status status;
}
