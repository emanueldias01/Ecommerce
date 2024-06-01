package br.com.emanuel.ecommerce.controller;

import br.com.emanuel.ecommerce.dto.*;
import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.service.ProdutoServiceAdmin;
import br.com.emanuel.ecommerce.service.ProdutoServiceClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServiceClient serviceClient;

    @Autowired
    private ProdutoServiceAdmin serviceAdmin;


    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> getAllProdutos(){
        return ResponseEntity.ok(serviceClient.getAllProdutosService());
    }
    @GetMapping("/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosByCategoria(@PathVariable Categoria categoria){
        return ResponseEntity.ok(serviceClient.getProdutosByCategoriaService(categoria));
    }

    @GetMapping("/search/name/{trechoNome}")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosByTrechoNome(@PathVariable String trechoNome){
        return ResponseEntity.ok(serviceClient.getProdutoTrechoNomeService(trechoNome));
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoById(@PathVariable String id){
        return ResponseEntity.ok(serviceClient.getProdutoByIdService(id));
    }

    @GetMapping("/promocao")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosNaPromocao(){
        return ResponseEntity.ok(serviceClient.getProdutosNaPromocaoService());
    }

    @GetMapping("/menorPreco/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosMenoresPrecosPorCategoria(@PathVariable Categoria categoria){
        return ResponseEntity.ok(serviceClient.getMenoresPrecosPorCategoriaService(categoria));
    }

    @GetMapping("/search/menorPreco/{trechoNome}")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosMenorPrecoPorPesquisa(@PathVariable String trechoNome){
        return ResponseEntity.ok(serviceClient.getMenoresPrecosPorPesquisaService(trechoNome));
    }

    @PostMapping("/createProduto")
    @Transactional
    public ResponseEntity<ProdutoResponseDTO> createProduto(@RequestBody ProdutoRequestDTO data, UriComponentsBuilder uriComponentsBuilder){
        var dto = serviceAdmin.createProdutoService(data);
        var uri = uriComponentsBuilder.path("{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/updateProduto")
    @Transactional
    public ResponseEntity<ProdutoResponseDTO> updateProduto(@RequestBody ProdutoUpdateDTO data){
        return ResponseEntity.ok(serviceAdmin.updateProdutoService(data));
    }

    @DeleteMapping("/deleteProduto/{id}")
    @Transactional
    public ResponseEntity deleteProduto(@PathVariable String id){
        serviceAdmin.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/setDesconto")
    @Transactional
    public ResponseEntity<ProdutoResponseDTO> controllerSetDesconto(@RequestBody ProdutoDescontoDTO data){
        return ResponseEntity.ok(serviceAdmin.setDescontoInProdutoService(data.id(), data.desconto()));
    }

    @PutMapping("/setStatus")
    @Transactional
    public ResponseEntity<ProdutoResponseDTO> controllerSetStatus(@RequestBody ProdutoStatusDTO data){
        return ResponseEntity.ok(serviceAdmin.setStatusService(data.id(), data.status()));
    }
}
