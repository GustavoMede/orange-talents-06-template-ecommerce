package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.annotation.ValidaLista;
import br.com.zupacademy.gustavo.mercadolivre.model.Caracteristica;
import br.com.zupacademy.gustavo.mercadolivre.model.Categoria;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.ProdutoCaracteristica;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal valor;
    @NotNull
    @Min(0)
    private Long qtdDisponivel;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @ValidaLista
    private List<ListasCaracteristicasRequest> caracteristicas;
    private String categoria;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProdutoRequest(String nome, BigDecimal valor, Long qtdDisponivel,
                          String descricao, List<ListasCaracteristicasRequest> caracteristicas, String categoria) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
    }

    public List<ProdutoCaracteristica> converteProdutoCaracteristica(EntityManager entityManager) {
        List<ProdutoCaracteristica> produtoCaracteristicas = new ArrayList<>();
        for (ListasCaracteristicasRequest request : this.caracteristicas) {
            Query query = entityManager.createQuery("select c from Caracteristica c where c.nome=:nome");
            query.setParameter("nome", request.getNome());
            Caracteristica caracteristica = (Caracteristica) query.getSingleResult();
            if (caracteristica != null) {
                ProdutoCaracteristica produtoCaracteristica = new ProdutoCaracteristica(request.getDescricao(), request.getNome());
                produtoCaracteristicas.add(produtoCaracteristica);
            }
        }
        return produtoCaracteristicas;
    }

    public Produto converte(Categoria categoria, List<ProdutoCaracteristica> caracteristicas) {
        return new Produto(this.nome, this.valor, this.qtdDisponivel, this.descricao, categoria, caracteristicas);
    }

    public Object procuraCategoria(EntityManager entityManager) {
        Query query = entityManager.createQuery("select c from Categoria c where c.nomeCategoria=:value");
        query.setParameter("value", this.categoria);
        return query.getSingleResult();

    }
}
