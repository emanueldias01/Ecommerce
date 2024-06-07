package br.com.emanuel.ecommerce.service;

import br.com.emanuel.ecommerce.dto.ProdutoRequestDTO;
import br.com.emanuel.ecommerce.dto.ProdutoResponseDTO;
import br.com.emanuel.ecommerce.dto.ProdutoUpdateDTO;
import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import br.com.emanuel.ecommerce.model.Status;
import br.com.emanuel.ecommerce.validations.ProdutoValidations;
import br.com.emanuel.ecommerce.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoServiceAdmin extends ProdutoValidations {

    @Autowired
    ProdutoRepository repository;

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

    public List<ProdutoResponseDTO> getAllProdutosAdminService(){
        return repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getAllByCategoriaProdutosAdminService(Categoria categoria){
        return repository.findByCategoria(categoria).stream().map(ProdutoResponseDTO::new).toList();
    }

    public List<ProdutoResponseDTO> getByTrechoNomeProdutoAdmin(String nome){
        return repository.buscaPorTrechoDeNome(nome).stream().map(ProdutoResponseDTO::new).toList();
    }
}
