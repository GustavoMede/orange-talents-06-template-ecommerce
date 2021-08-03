package br.com.zupacademy.gustavo.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ProdutoCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public ProdutoCaracteristica() {
    }

    public ProdutoCaracteristica(String nome, String descricao, Produto produto) {
        this.descricao = descricao;
        this.produto = produto;
        this.nome = nome;
    }

    public ProdutoCaracteristica(String descricao, String nome) {
        this.descricao = descricao;
        this.nome = nome;
    }

    public ProdutoCaracteristica converte(@NotNull @Valid Produto produto) {
        return new ProdutoCaracteristica(nome, descricao, produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoCaracteristica that = (ProdutoCaracteristica) o;
        return nome.equals(that.nome) && descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
