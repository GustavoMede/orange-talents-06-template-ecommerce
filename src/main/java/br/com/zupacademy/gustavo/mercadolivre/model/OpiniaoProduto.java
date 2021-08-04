package br.com.zupacademy.gustavo.mercadolivre.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public OpiniaoProduto() {
    }

    public OpiniaoProduto(@Min(1) @Max(5) Integer nota, @NotBlank String titulo,
                          @NotBlank @Length(max = 500) String descricao, @NotNull Usuario usuario,
                          @NotNull Produto produto) {
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

    public Produto getProduto() {
        return produto;
    }
}
