package br.com.emanuel.ecommerce.email;

import br.com.emanuel.ecommerce.security.token.TokenService;
import br.com.emanuel.ecommerce.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailRegisterService {

    private final String createAccountSubject = "Vamos criar sua conta!";

    private final String mensagemDeCriacaoDeConta = """
            Olá, tudo bem?
            Vi que você está se registrando na nossa plataforma,
            para continuar com seu cadastro, copie o número e cole no
            aplicativo para que possamos validar este email.
            
            OBS: O número só é válido por 10 minutos!
            
            COPIE E COLE:
            
            """;

    @Autowired
    JavaMailSender mailSender;

    public void sendEmailRegister(Email email){
        var mensagem = new SimpleMailMessage();
        mensagem.setTo(email.getTo());
        mensagem.setSubject(createAccountSubject);

        var user = new User();
        user.setEmail(email.getTo());

        var token = 0;

        mensagem.setText(mensagemDeCriacaoDeConta + token);

        mailSender.send(mensagem);
    }
}
