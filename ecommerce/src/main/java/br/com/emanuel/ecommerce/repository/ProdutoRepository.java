package br.com.emanuel.ecommerce.repository;

import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query(value = "SELECT * FROM produtos WHERE categoria = :categoria AND status = 'ativo'", nativeQuery = true)
    List<Produto> findByCategoriaClient(@Param("categoria") Categoria categoria);

    @Query(value = "SELECT * FROM produtos WHERE nome = :nome AND status = 'ativo'", nativeQuery = true)
    Optional<Produto> findByNomeClient(@Param("nome") String nome);

    @Query(value = "SELECT * FROM produtos WHERE nome LIKE %:trechoNome% AND status = 'ativo'", nativeQuery = true)
    List<Produto> buscaPorTrechoDeNomeClient(@Param("trechoNome") String trechoNome);

    @Query(value = "SELECT * FROM produtos WHERE desconto > 0 AND status = 'ativo'", nativeQuery = true)
    List<Produto> buscaProdutosNaPromocaoClient();

    @Query(value = "SELECT * FROM produtos WHERE categoria = :categoria AND status = 'ativo' ORDER BY preco ASC", nativeQuery = true)
    List<Produto> findByCategoriaOrderByPrecoAscClient(@Param("categoria") Categoria categoria);

    @Query(value = "SELECT * FROM produtos WHERE nome LIKE %:trechoNome% AND status = 'ativo' ORDER BY preco ASC", nativeQuery = true)
    List<Produto> buscaMenoresPrecosPorPesquisaClient(@Param("trechoNome") String trechoNome);

    @Query(value = "SELECT * FROM produtos WHERE status = 'ativo'", nativeQuery = true)
    List<Produto> findAllClient();
}
