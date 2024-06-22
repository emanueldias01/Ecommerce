package br.com.emanuel.ecommerce.email.repository;

import br.com.emanuel.ecommerce.email.EmailAutenticando;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface EmailAutenticacaoRepository extends JpaRepository<EmailAutenticando, Long> {

    @Query(value = "SELECT numero FROM emails_autenticando WHERE email = :email", nativeQuery = true)
    int buscaNumeroAssociadoAoEmail(@Param("email") String email);

    @Query(value = "SELECT validacacao FROM emails_autenticando WHERE email = :email", nativeQuery = true)
    LocalDateTime buscaDataDeValidacao(@Param("email") String email);

    void deleteByEmail(String email);
}
