package br.com.emanuel.ecommerce.controller;

import br.com.emanuel.ecommerce.dto.*;
import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.service.ProdutoServiceAdmin;
import br.com.emanuel.ecommerce.service.ProdutoServiceClient;
import io.swagger.v3.oas.annotations.Operation;
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


    //Client Methods
    @GetMapping
    @Operation(description = "Devolve todos os produtos ativos")
    public ResponseEntity<List<ProdutoResponseDTO>> getAllProdutos(){
        return ResponseEntity.ok(serviceClient.getAllProdutosService());
    }
    @GetMapping("/{categoria}")
    @Operation(description = "Devolve todos os produtos ativos da categoria fornecida na URL")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosByCategoria(@PathVariable Categoria categoria){
        return ResponseEntity.ok(serviceClient.getProdutosByCategoriaService(categoria));
    }

    @GetMapping("/search/name/{trechoNome}")
    @Operation(description = "Devolve todos os produtos ativos que possuem o trecho de nome passado na URL")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosByTrechoNome(@PathVariable String trechoNome){
        return ResponseEntity.ok(serviceClient.getProdutoTrechoNomeService(trechoNome));
    }

    @GetMapping("/search/id/{id}")
    @Operation(description = "Devolve o produto com o id passado na URL")
    public ResponseEntity<ProdutoResponseDTO> getProdutoById(@PathVariable String id){
        return ResponseEntity.ok(serviceClient.getProdutoByIdService(id));
    }

    @GetMapping("/promocao")
    @Operation(description = "Devolve todos os produtos ativos que estão na promocao")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosNaPromocao(){
        return ResponseEntity.ok(serviceClient.getProdutosNaPromocaoService());
    }

    @GetMapping("/menorPreco/{categoria}")
    @Operation(description = "Devolve em ordem os produtos ativos com menores precos da categoria passada na URL")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosMenoresPrecosPorCategoria(@PathVariable Categoria categoria){
        return ResponseEntity.ok(serviceClient.getMenoresPrecosPorCategoriaService(categoria));
    }

    @GetMapping("/search/menorPreco/{trechoNome}")
    @Operation(description = "Devolve os menores precos dos produtos ativos com o trecho de nome passado na URL")
    public ResponseEntity<List<ProdutoResponseDTO>> getProdutosMenorPrecoPorPesquisa(@PathVariable String trechoNome){
        return ResponseEntity.ok(serviceClient.getMenoresPrecosPorPesquisaService(trechoNome));
    }

    //Adm Methods
    @PostMapping("/createProduto")
    @Transactional
    @Operation(description = "cria um produto com os parâmetros passados com JSON")
    public ResponseEntity<ProdutoResponseDTO> createProduto(@RequestBody ProdutoRequestDTO data, UriComponentsBuilder uriComponentsBuilder){
        var dto = serviceAdmin.createProdutoService(data);
        var uri = uriComponentsBuilder.path("{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/updateProduto")
    @Transactional
    @Operation(description = "atualiza informacoes do produto com parâmetros passados no JSON")
    public ResponseEntity<ProdutoResponseDTO> updateProduto(@RequestBody ProdutoUpdateDTO data){
        return ResponseEntity.ok(serviceAdmin.updateProdutoService(data));
    }

    @DeleteMapping("/deleteProduto/{id}")
    @Transactional
    @Operation(description = "deleta um produto pelo id passado na URL")
    public ResponseEntity deleteProduto(@PathVariable String id){
        serviceAdmin.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/setDesconto")
    @Transactional
    @Operation(description = "atualiza desconto com parâmetros passado no JSON")
    public ResponseEntity<ProdutoResponseDTO> controllerSetDesconto(@RequestBody ProdutoDescontoDTO data){
        return ResponseEntity.ok(serviceAdmin.setDescontoInProdutoService(data.id(), data.desconto()));
    }

    @PutMapping("/setStatus")
    @Transactional
    @Operation(description = "muda status com parâmetros passados no JSON")
    public ResponseEntity<ProdutoResponseDTO> controllerSetStatus(@RequestBody ProdutoStatusDTO data){
        return ResponseEntity.ok(serviceAdmin.setStatusService(data.id(), data.status()));
    }

    @GetMapping("/admin/getAll")
    @Operation(description = "devolve todos os produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> getAllProdutosAdmin(){
        return ResponseEntity.ok(serviceAdmin.getAllProdutosAdminService());
    }

    @GetMapping("/admin/getByNome/{trechoNome}")
    @Operation(description = "devolve todos os produtos que possui o trecho de nome passado na URL")
    public ResponseEntity<List<ProdutoResponseDTO>> getByTrechoNomeAdmin(@PathVariable String trechoNome){
        return ResponseEntity.ok(serviceAdmin.getByTrechoNomeProdutoAdmin(trechoNome));
    }

    @GetMapping("/admin/{categoria}")
    @Operation(description = "devolve todos os produtos da categoria passada na URL")
    public ResponseEntity<List<ProdutoResponseDTO>> getByCategoriaAdmin(@PathVariable Categoria categoria){
        return ResponseEntity.ok(serviceAdmin.getAllByCategoriaProdutosAdminService(categoria));
    }
}
