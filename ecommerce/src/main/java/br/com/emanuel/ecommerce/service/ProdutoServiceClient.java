package br.com.emanuel.ecommerce.service;

import br.com.emanuel.ecommerce.dto.ProdutoResponseDTO;
import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import br.com.emanuel.ecommerce.repository.ProdutoRepository;
import br.com.emanuel.ecommerce.validations.ProdutoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoServiceClient extends ProdutoValidations {

    @Autowired
    private ProdutoRepository repository;


    public Page<ProdutoResponseDTO> getAllProdutosService(Pageable paginacao){
        return repository.findAllClient(paginacao).map(ProdutoResponseDTO::new);
    }

    public Page<ProdutoResponseDTO> getProdutosByCategoriaService(Categoria categoria, Pageable paginacao){
        return repository.findByCategoriaClient(categoria, paginacao).map(ProdutoResponseDTO::new);
    }

    public Page<ProdutoResponseDTO> getProdutoTrechoNomeService(String trechoNome, Pageable paginacao){
        return repository.buscaPorTrechoDeNomeClient(trechoNome, paginacao).map(ProdutoResponseDTO::new);
    }

    public ProdutoResponseDTO getProdutoByIdService(String idString) {
        UUID id = UUID.fromString(idString);
        Produto produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponseDTO(produto);
    }

    public Page<ProdutoResponseDTO> getProdutosNaPromocaoService(Pageable paginacao){
        return repository.buscaProdutosNaPromocaoClient(paginacao).map(ProdutoResponseDTO::new);
    }

    public Page<ProdutoResponseDTO> getMenoresPrecosPorCategoriaService(Categoria categoria, Pageable paginacao){
        return repository.findByCategoriaOrderByPrecoAscClient(categoria, paginacao).map(ProdutoResponseDTO::new);
    }

    public Page<ProdutoResponseDTO> getMenoresPrecosPorPesquisaService(String trechoNome, Pageable paginacao){
        return repository.buscaMenoresPrecosPorPesquisaClient(trechoNome, paginacao).map(ProdutoResponseDTO::new);
    }
}