package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ProdutoDto {

    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Set<ProdutoCaracteristica> produtoCaracteristica;
    private Set<FotoProduto> fotos;
    private Set<OpiniaoProduto> opinioes;
    private SortedSet<String> perguntas;
    private Integer totalNotas;
    private Double mediaNotas;

    public ProdutoDto(Produto produto, Integer notas, Double mediaNotas) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.produtoCaracteristica = produto.getProdutoCaracteristica();
        this.fotos = produto.getFotos();
        this.opinioes = produto.getOpinioes();
        this.perguntas = (SortedSet<String>) produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
        this.totalNotas = notas;
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

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Integer getTotalNotas() {
        return totalNotas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }
}
