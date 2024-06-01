package br.com.emanuel.ecommerce.service;

import br.com.emanuel.ecommerce.dto.ProdutoRequestDTO;
import br.com.emanuel.ecommerce.dto.ProdutoResponseDTO;
import br.com.emanuel.ecommerce.dto.ProdutoUpdateDTO;
import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import br.com.emanuel.ecommerce.model.Status;
import br.com.emanuel.ecommerce.repository.ProdutoRepository;
import br.com.emanuel.ecommerce.validations.ProdutoValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService extends ProdutoValidations {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoResponseDTO> getAllProdutosService(){
        return repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getProdutosByCategoriaService(Categoria categoria){
        return repository.findByCategoria(categoria).stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getProdutoTrechoNomeService(String trechoNome){
        return repository.buscaPorTrechoDeNome(trechoNome).stream().map(ProdutoResponseDTO::new).toList();
    }

    public ProdutoResponseDTO getProdutoByIdService(String idString) {
        UUID id = UUID.fromString(idString);
        Produto produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponseDTO(produto);
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
            verificaSeProdutoExiste(data.nome());
            Produto produtoSave = new Produto(data);
            setValoresPadroesDeProdutoCriado(produtoSave);
            repository.save(produtoSave);
            return new ProdutoResponseDTO(produtoSave);
    }

    public ProdutoResponseDTO setDescontoInProdutoService(String idString, Double desconto){
        UUID id = UUID.fromString(idString);
        return aplicaDescontoNoProduto(id, desconto);
    }

    public ProdutoResponseDTO setStatusService(String idString, Status status){
        UUID id = UUID.fromString(idString);
        var produto = repository.getReferenceById(id);
        produto.setStatus(status);
        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO updateProdutoService(ProdutoUpdateDTO data){
        var produto = repository.getReferenceById(data.getId());
        produto.updateInfo(data);
        return new ProdutoResponseDTO(produto);
    }

    public void deleteProduto(String idString){
        UUID id = UUID.fromString(idString);
        repository.deleteById(id);
    }
}