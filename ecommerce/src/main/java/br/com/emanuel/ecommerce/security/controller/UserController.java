package br.com.emanuel.ecommerce.security.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailRegisterService emailRegisterService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }


    @PostMapping("/verifyEmail")
    public ResponseEntity verifyEmail(@RequestBody EmailRegisterDTO dto) {

        if (repository.findByEmail(dto.email()) != null) {
            throw new UsuarioJaExisteException("Já existe alguém com esse EMAIL registrado");
        }

        emailRegisterService.sendEmailRegister((new Email(dto.email())));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verifyTokenEmail")
    public ResponseEntity verifyTokenEmail(@RequestBody TokenDTO dto){

        tokenService.validateToken(dto.token());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/finalRegister")
    public ResponseEntity finalRegister(@RequestBody RegisterDTO dto) {

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

        return ResponseEntity.ok().build();
    }
}
