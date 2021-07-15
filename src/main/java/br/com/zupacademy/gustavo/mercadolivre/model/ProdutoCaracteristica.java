package br.com.zupacademy.gustavo.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class ProdutoCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    @ManyToOne
    private Produto produto;

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
}
