package br.com.emanuel.ecommerce.model;

import br.com.emanuel.ecommerce.dto.ProdutoRequestDTO;
import br.com.emanuel.ecommerce.dto.ProdutoUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
        this.nome = data.nome();
        this.descricao = data.descricao();
        this.categoria = data.categoria();
        this.preco = data.preco();
        this.imagem = data.imagem();
    }

    public void updateInfo(ProdutoUpdateDTO data) {
        if(data.getNome() != null){
            this.nome = data.getNome();
        }
        if(data.getDescricao() != null){
            this.descricao = data.getDescricao();
        }
        if(data.getCategoria() != null){
            this.categoria = data.getCategoria();
        }
        if(data.getPreco() != null){
            this.preco = data.getPreco();
        }
        if(data.getImagem() != null){
            this.imagem = data.getImagem();
        }
        if(data.getStatus() != null){
            this.status = data.getStatus();
        }
    }
}
