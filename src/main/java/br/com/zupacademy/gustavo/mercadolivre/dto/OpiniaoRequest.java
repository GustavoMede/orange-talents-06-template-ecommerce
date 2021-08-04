package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.model.OpiniaoProduto;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class OpiniaoRequest {

    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Length(max = 500)
    private String descricao;

    public OpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
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

    public Produto encontraProduto(EntityManager entityManager, Long id) {
        Optional<Produto> produto = Optional.ofNullable(entityManager.find(Produto.class, id));
        return produto.orElse(null);
    }

    public OpiniaoProduto converte(Produto produto, Usuario usuario) {
        return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
    }
}
