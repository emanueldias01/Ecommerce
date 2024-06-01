package br.com.emanuel.ecommerce.dto;

import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import br.com.emanuel.ecommerce.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponseDTO {

    private String id;

    private String nome;

    private Categoria categoria;

    private String descricao;

    private BigDecimal preco;

    private String imagem;

    private Double desconto;

    private Status status;

    public ProdutoResponseDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.imagem = produto.getImagem();
        this.desconto = produto.getDesconto();
        this.status = produto.getStatus();
    }
}
