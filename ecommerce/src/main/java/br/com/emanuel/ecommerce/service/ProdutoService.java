package br.com.emanuel.ecommerce.service;

import br.com.emanuel.ecommerce.dto.ProdutoResponseDTO;
import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
