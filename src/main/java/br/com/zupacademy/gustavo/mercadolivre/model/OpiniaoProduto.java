package br.com.zupacademy.gustavo.mercadolivre.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    @ManyToOne
    @NotNull
    private Usuario usuario;
    @ManyToOne
    @NotNull
    private Produto produto;

    @Deprecated
    public OpiniaoProduto() {
    }

    public OpiniaoProduto(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuario() {
        return usuario.getUsername();
    }
}
