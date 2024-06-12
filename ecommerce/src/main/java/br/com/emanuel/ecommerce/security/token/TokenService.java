package br.com.emanuel.ecommerce.security.token;

import br.com.emanuel.ecommerce.security.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.key.secret.security}")
    private String secret;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try{
            var token = JWT.create()
                    .withIssuer("ecommerce-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateTimeValidTokenUser())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex){
            throw new RuntimeException("ERROR: " + ex.getMessage());
        }
    }

    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try{
            return JWT.require(algorithm)
                    .withIssuer("ecommerce-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex){
            return "";
        }
    }

    private Instant generateTimeValidTokenUser(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String generateTokenEmail(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try{
            var token = JWT.create()
                    .withIssuer("ecommerce-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateTimeValidTokenEmail())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex){
            throw new RuntimeException("ERROR: " + ex.getMessage());
        }
    }

    private Instant generateTimeValidTokenEmail(){
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
    }
}
