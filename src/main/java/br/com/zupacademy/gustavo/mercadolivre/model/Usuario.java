package br.com.zupacademy.gustavo.mercadolivre.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank @Email
    private String login;
    @NotNull
    private int senha;
    @NotNull @PastOrPresent @Column(updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Usuario() {
    }

    public Usuario(@NotBlank @Email String login, @NotBlank String senha) {
        Assert.isTrue(StringUtils.hasLength(login), "O email não pode ser em branco.");
        Assert.isTrue(senha.length() >= 6, "A senha deve ter no mínimo 6 caracteres.");
        this.login = login;
        this.senha = senha.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senha);
    }
}
