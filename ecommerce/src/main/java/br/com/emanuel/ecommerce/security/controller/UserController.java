package br.com.emanuel.ecommerce.security.controller;

import br.com.emanuel.ecommerce.security.dto.LoginRequestDTO;
import br.com.emanuel.ecommerce.security.dto.RegisterDTO;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO dto){
        if(repository.findByUsername(dto.username()) != null){
            throw new RuntimeException("User has found");
        }

        User userSave = new User();
        String passwordEncrypt = new BCryptPasswordEncoder().encode(dto.password());

        userSave.setUsername(dto.username());
        userSave.setPassword(passwordEncrypt);
        userSave.setRole(dto.role());

        repository.save(userSave);

        return ResponseEntity.ok().build();
    }
}
