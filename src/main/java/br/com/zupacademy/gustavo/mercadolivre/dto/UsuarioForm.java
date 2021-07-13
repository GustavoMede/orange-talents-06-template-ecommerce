package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.annotation.CampoDuplicado;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @NotBlank @Email @CampoDuplicado(domainClass = Usuario.class, fieldName = "email")
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;

    public UsuarioForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converte(UsuarioForm form){
        return new Usuario(form.email, form.senha);
    }
}
