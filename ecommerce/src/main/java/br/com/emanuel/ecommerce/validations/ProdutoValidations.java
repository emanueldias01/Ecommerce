package br.com.emanuel.ecommerce.validations;

import br.com.emanuel.ecommerce.dto.ProdutoResponseDTO;
import br.com.emanuel.ecommerce.exceptions.DescontoInvalidoException;
import br.com.emanuel.ecommerce.exceptions.ProdutoExistException;
import br.com.emanuel.ecommerce.model.Produto;
import br.com.emanuel.ecommerce.model.Status;
import br.com.emanuel.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoValidations {

    @Autowired
    private ProdutoRepository repository;

    protected void verificaSeProdutoExiste(String nome){
        var produtoManipulavel = repository.findByNomeClient(nome);
        if(produtoManipulavel.isEmpty()){}
        else {
            throw new ProdutoExistException("O produto que voce está tendando registrar já existe");
        }
    }
    protected ProdutoResponseDTO aplicaDescontoNoProduto(UUID id, Double desconto){
        verificaDesconto(desconto);
        var produto = repository.getReferenceById(id);
        produto.setDesconto(desconto);
        var precoAtual = produto.getPreco();
        var cem = new BigDecimal("100");
        var descontoBigDecimal = new BigDecimal(desconto);

        var porcentagemDesconto = cem.subtract(descontoBigDecimal);

        var novoPreco = precoAtual.multiply(porcentagemDesconto).divide(cem);
        produto.setPreco(novoPreco);
        return new ProdutoResponseDTO(produto);
    }

    private void verificaDesconto(Double desconto){
        if(desconto > 0.0){}
        else {
            throw new DescontoInvalidoException("O desconto que está tentando inserir é inválido");
        }
    }

    protected void setValoresPadroesDeProdutoCriado(Produto produto){
        produto.setDesconto(0.0);
        produto.setStatus(Status.ativo);
    }

}
