package br.com.zupacademy.gustavo.mercadolivre.model;

import br.com.zupacademy.gustavo.mercadolivre.dto.PerguntaRequestDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class PerguntaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String titulo;
    @CreationTimestamp
    private LocalDateTime dataCriacao;
    @ManyToOne
    @NotNull
    private Usuario usuario;
    @ManyToOne
    @NotNull
    private Produto produto;

    @Deprecated
    public PerguntaProduto() {
    }

    public PerguntaProduto(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getUsuario() {
        return usuario.getUsername();
    }
    public PerguntaRequestDto converteDto() {
        return new PerguntaRequestDto(this.titulo);
    }
}
