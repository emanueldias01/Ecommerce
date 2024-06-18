package br.com.emanuel.ecommerce.repository;

import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query("SELECT p FROM Produto p WHERE p.categoria = :categoria AND p.status = br.com.emanuel.ecommerce.model.Status.ativo")
    Page<Produto> findByCategoriaClient(@Param("categoria") Categoria categoria, Pageable paginacao);


    @Query(value = "SELECT * FROM produtos WHERE nome = :nome", nativeQuery = true)
    Optional<Produto> findByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM produtos WHERE nome LIKE %:trechoNome% AND status = 'ativo'", nativeQuery = true)
    Page<Produto> buscaPorTrechoDeNomeClient(@Param("trechoNome") String trechoNome, Pageable paginacao);

    @Query(value = "SELECT * FROM produtos WHERE desconto > 0 AND status = 'ativo'", nativeQuery = true)
    Page<Produto> buscaProdutosNaPromocaoClient(Pageable paginacao);

    @Query("SELECT p FROM Produto p WHERE p.categoria = :categoria AND p.status = br.com.emanuel.ecommerce.model.Status.ativo ORDER BY p.preco ASC")
    Page<Produto> findByCategoriaOrderByPrecoAscClient(@Param("categoria") Categoria categoria, Pageable paginacao);

    @Query(value = "SELECT * FROM produtos WHERE nome LIKE %:trechoNome% AND status = 'ativo' ORDER BY preco ASC", nativeQuery = true)
    Page<Produto> buscaMenoresPrecosPorPesquisaClient(@Param("trechoNome") String trechoNome, Pageable paginacao);

    @Query(value = "SELECT * FROM produtos WHERE status = 'ativo'", nativeQuery = true)
    Page<Produto> findAllClient(Pageable paginacao);

    @Query(value = "SELECT * FROM produtos WHERE nome LIKE %:nome%", nativeQuery = true)
    Page<Produto> buscaPorTrechoDeNome(String nome, Pageable paginacao);

    Page<Produto> findByCategoria(Categoria categoria, Pageable paginacao);
}
