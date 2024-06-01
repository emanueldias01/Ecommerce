package br.com.emanuel.ecommerce.service;

import br.com.emanuel.ecommerce.dto.ProdutoRequestDTO;
import br.com.emanuel.ecommerce.dto.ProdutoResponseDTO;
import br.com.emanuel.ecommerce.exceptions.DescontoInvalidoException;
import br.com.emanuel.ecommerce.exceptions.ProdutoExistException;
import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import br.com.emanuel.ecommerce.model.Status;
import br.com.emanuel.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public List<ProdutoResponseDTO> getAllProdutosService(){
        return repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getProdutosByCategoriaService(Categoria categoria){
        return repository.findByCategoria(categoria).stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getProdutoTrechoNomeService(String trechoNome){
        return repository.buscaPorTrechoDeNome(trechoNome).stream().map(ProdutoResponseDTO::new).toList();
    }

    public ProdutoResponseDTO getProdutoByIdService(String id){
        return (ProdutoResponseDTO) repository.findById(id).stream().map(p -> new ProdutoResponseDTO(p));
    }

    public List<ProdutoResponseDTO> getProdutosNaPromocaoService(){
        return repository.buscaProdutosNaPromocao().stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getMenoresPrecosPorCategoriaService(Categoria categoria){
        return repository.findByCategoriaOrderByPrecoAsc(categoria).stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getMenoresPrecosPorPesquisaService(String trechoNome){
        return repository.buscaMenoresPrecosPorPesquisa(trechoNome).stream().map(ProdutoResponseDTO::new).toList();
    }

    public ProdutoResponseDTO createProdutoService(ProdutoRequestDTO data){
        var verificaProduto = repository.findByNome(data.getNome());
        if(verificaProduto.isEmpty()){
            Produto produtoSave = new Produto(data);
            produtoSave.setDesconto(0.0);
            produtoSave.setStatus(Status.ativo);
            repository.save(produtoSave);
            return new ProdutoResponseDTO(produtoSave);
        }else {
            throw new ProdutoExistException("O produto que voce está tendando registrar já existe");
        }
    }

    public ProdutoResponseDTO setDescontoInProdutoService(String id, Double desconto){
        if(desconto > 0){
            var produto = repository.getReferenceById(id);
            produto.setDesconto(desconto);
            var precoAtual = produto.getPreco();
            var cem = new BigDecimal("100");
            var descontoBigDecimal = new BigDecimal(desconto);
            var porcentagemPreco = cem.subtract(descontoBigDecimal);
            var novoPreco = precoAtual.multiply(porcentagemPreco);
            produto.setPreco(novoPreco);
            return new ProdutoResponseDTO(produto);
        }else {
            throw new DescontoInvalidoException("O desconto inserido é inválido");
        }
    }

    public ProdutoResponseDTO setStatus(String id, Status status){
        var produto = repository.getReferenceById(id);
        produto.setStatus(status);
        return new ProdutoResponseDTO(produto);
    }
}
