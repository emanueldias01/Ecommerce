package br.com.emanuel.ecommerce.dto;

import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Status;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoUpdateDTO {
    private String id;

    private String nome;

    private Categoria categoria;

    private String descricao;

    private BigDecimal preco;

    private String imagem;

    private Double desconto;

    private Status status;
}
