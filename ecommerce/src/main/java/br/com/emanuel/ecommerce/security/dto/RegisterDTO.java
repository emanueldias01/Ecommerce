package br.com.emanuel.ecommerce.security.dto;

import br.com.emanuel.ecommerce.security.user.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
