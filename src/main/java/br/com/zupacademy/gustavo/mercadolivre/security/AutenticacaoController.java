package br.com.zupacademy.gustavo.mercadolivre.security;

import br.com.zupacademy.gustavo.mercadolivre.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping
    public ResponseEntity<?> autenticacao(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converte();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenManager.gerarToken(authentication);
            return ResponseEntity.ok(token);
        }catch(AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }



    }
}
