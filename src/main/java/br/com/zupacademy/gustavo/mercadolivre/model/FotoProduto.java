package br.com.zupacademy.gustavo.mercadolivre.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class FotoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @URL
    @NotBlank
    private String link;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @Deprecated
    public FotoProduto() {
    }

    public FotoProduto(String link, @NotNull @Valid Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FotoProduto that = (FotoProduto) o;
        return link.equals(that.link) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, produto);
    }

    public String getLink() {
        return link;
    }
}
