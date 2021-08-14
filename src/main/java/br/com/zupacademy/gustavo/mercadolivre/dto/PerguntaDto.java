package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class PerguntaDto {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaDto(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Produto encontraProduto(EntityManager entityManager, Long id) {
        Optional<Produto> produto = Optional.ofNullable(entityManager.find(Produto.class, id));
        return produto.orElse(null);
    }

    public PerguntaProduto converte(Produto produto, Usuario usuario) {
        return new PerguntaProduto(this.titulo, usuario, produto);
    }
}
