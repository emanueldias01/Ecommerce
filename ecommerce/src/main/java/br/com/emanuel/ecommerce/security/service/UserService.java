package br.com.emanuel.ecommerce.security.service;

import br.com.emanuel.ecommerce.email.Email;
import br.com.emanuel.ecommerce.email.EmailRegisterDTO;
import br.com.emanuel.ecommerce.email.EmailRegisterService;
import br.com.emanuel.ecommerce.exceptions.UsuarioJaExisteException;
import br.com.emanuel.ecommerce.security.dto.LoginRequestDTO;
import br.com.emanuel.ecommerce.security.dto.RegisterDTO;
import br.com.emanuel.ecommerce.security.dto.TokenDTO;
import br.com.emanuel.ecommerce.security.token.TokenService;
import br.com.emanuel.ecommerce.security.user.User;
import br.com.emanuel.ecommerce.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailRegisterService emailRegisterService;

    public String loginService(LoginRequestDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }

    public void verifyEmailService(EmailRegisterDTO dto){
        if (repository.findByEmail(dto.email()) != null) {
            throw new UsuarioJaExisteException("Já existe alguém com esse EMAIL registrado");
        }

        emailRegisterService.sendEmailRegister((new Email(dto.email())));
    }

    public void validateEmailTokenService(TokenDTO dto){
        tokenService.validateToken(dto.token());
    }

    public void finalRegisterUserService(RegisterDTO dto){
        if (repository.findByUsername(dto.username()) != null) {
            throw new UsuarioJaExisteException("Já existe alguém com esse USERNAME registrado");

        }
        User userSave = new User();
        String passwordEncrypt = new BCryptPasswordEncoder().encode(dto.password());

        userSave.setUsername(dto.username());
        userSave.setEmail(dto.email());
        userSave.setPassword(passwordEncrypt);
        userSave.setRole(dto.role());

        repository.save(userSave);
    }
}
