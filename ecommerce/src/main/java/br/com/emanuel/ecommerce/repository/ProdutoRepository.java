package br.com.emanuel.ecommerce.repository;

import br.com.emanuel.ecommerce.model.Categoria;
import br.com.emanuel.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    List<Produto> findByCategoria(Categoria categoria);

    Optional<Produto> findByNome(String nome);

    @Query(value = "SELECT * FROM produtos WHERE nome LIKE %:trechoNome%", nativeQuery = true)
    List<Produto> buscaPorTrechoDeNome(@Param("trechoNome") String trechoNome);

    @Query(value = "SELECT * FROM produtos WHERE desconto > 0", nativeQuery = true)
    List<Produto> buscaProdutosNaPromocao();

    List<Produto> findByCategoriaOrderByPrecoAsc(Categoria categoria);

    @Query(value = "SELECT * FROM produtos WHERE nome LIKE %:trechoNome% ORDER BY preco ASC", nativeQuery = true)
    List<Produto> buscaMenoresPrecosPorPesquisa(@Param("trechoNome") String trechoNome);
}
