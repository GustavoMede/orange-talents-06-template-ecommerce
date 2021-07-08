package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @NotBlank @Email
    private String login;
    @NotBlank @Size(min = 6)
    private String senha;

    public UsuarioForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converte(UsuarioForm form){
        return new Usuario(form.login, form.senha);
    }
}
