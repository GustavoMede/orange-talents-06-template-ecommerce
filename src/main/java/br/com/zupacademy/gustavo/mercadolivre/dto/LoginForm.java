package br.com.zupacademy.gustavo.mercadolivre.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public UsernamePasswordAuthenticationToken converte() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
