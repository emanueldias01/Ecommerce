package br.com.emanuel.ecommerce.email.repository;

import br.com.emanuel.ecommerce.email.EmailAutenticando;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAutenticacaoRepository extends JpaRepository<EmailAutenticando, Long> {
}
