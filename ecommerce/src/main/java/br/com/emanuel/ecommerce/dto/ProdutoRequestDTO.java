package br.com.emanuel.ecommerce.dto;

import br.com.emanuel.ecommerce.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;

public record ProdutoRequestDTO(

    @NotBlank
     String nome,

    @NotBlank
     Categoria categoria,

    @NotBlank
     String descricao,

    @NotNull
     BigDecimal preco,

    @NotBlank
    String imagem
)
{
}
