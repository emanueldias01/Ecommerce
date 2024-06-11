package br.com.emanuel.ecommerce.security.user;

public enum UserRole {
    USER("user"),
    ADMIN("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }
}
