package br.com.emanuel.ecommerce.model;

import br.com.emanuel.ecommerce.dto.ProdutoRequestDTO;
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

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Produto(ProdutoRequestDTO data) {
        this.nome = data.getNome();
        this.descricao = data.getDescricao();
        this.categoria = data.getCategoria();
        this.preco = data.getPreco();
        this.imagem = data.getImagem();
    }

}
