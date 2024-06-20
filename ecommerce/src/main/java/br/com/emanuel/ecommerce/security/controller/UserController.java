package br.com.emanuel.ecommerce.security.controller;

import br.com.emanuel.ecommerce.email.Email;
import br.com.emanuel.ecommerce.email.EmailRegisterDTO;
import br.com.emanuel.ecommerce.email.EmailRegisterService;
import br.com.emanuel.ecommerce.exceptions.UsuarioJaExisteException;
import br.com.emanuel.ecommerce.security.dto.LoginRequestDTO;
import br.com.emanuel.ecommerce.security.dto.RegisterDTO;
import br.com.emanuel.ecommerce.security.dto.TokenDTO;
import br.com.emanuel.ecommerce.security.service.UserService;
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
    UserService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO dto) {
        var token = service.loginService(dto);
        return ResponseEntity.ok(new TokenDTO(token));
    }


    @PostMapping("/verifyEmail")
    public ResponseEntity verifyEmail(@RequestBody EmailRegisterDTO dto) {
        service.verifyEmailService(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verifyTokenEmail")
    public ResponseEntity verifyTokenEmail(@RequestBody TokenDTO dto){
        service.validateEmailTokenService(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/finalRegister")
    public ResponseEntity finalRegister(@RequestBody RegisterDTO dto) {
        service.finalRegisterUserService(dto);
        return ResponseEntity.ok().build();
    }
}
