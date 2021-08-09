package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.model.*;

import java.math.BigDecimal;
import java.util.Set;

public class ProdutoDto {

    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Set<ProdutoCaracteristica> produtoCaracteristica;
    private Set<FotoProduto> fotos;
    private Set<OpiniaoProduto> opinioes;
    private Set<PerguntaProduto> perguntas;
    private Integer totalNotas;
    private Double mediaNotas;

    public ProdutoDto(String nome, BigDecimal valor, String descricao,
                      Set<ProdutoCaracteristica> produtoCaracteristica, Set<FotoProduto> fotos,
                      Set<OpiniaoProduto> opinioes, Set<PerguntaProduto> perguntas, Integer totalNotas,
                      Double mediaNotas) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.produtoCaracteristica = produtoCaracteristica;
        this.fotos = fotos;
        this.opinioes = opinioes;
        this.perguntas = perguntas;
        this.totalNotas = totalNotas;
        this.mediaNotas = mediaNotas;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<ProdutoCaracteristica> getProdutoCaracteristica() {
        return produtoCaracteristica;
    }

    public Set<FotoProduto> getFotos() {
        return fotos;
    }

    public Set<OpiniaoProduto> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaProduto> getPerguntas() {
        return perguntas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }
}
