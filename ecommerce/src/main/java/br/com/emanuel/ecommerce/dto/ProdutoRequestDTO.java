package br.com.emanuel.ecommerce.dto;

import br.com.emanuel.ecommerce.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private Categoria categoria;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @NotBlank
    private String imagem;

}
