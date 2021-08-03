package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.annotation.ValidaListaCaracteristicas;
import br.com.zupacademy.gustavo.mercadolivre.exception.ProdutoDuplicadoException;
import br.com.zupacademy.gustavo.mercadolivre.model.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

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
    @ValidaListaCaracteristicas
    private List<ListasCaracteristicasRequest> caracteristicas;
    private String categoria;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProdutoRequest(String nome, BigDecimal valor, Long qtdDisponivel,
                          String descricao, List<ListasCaracteristicasRequest> caracteristicas,
                          String categoria) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public List<ListasCaracteristicasRequest> getCaracteristicas() {
        return caracteristicas;
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

    public Produto converte(Categoria categoria, List<ProdutoCaracteristica> caracteristicas, Usuario usuario) {
        return new Produto(this.nome, this.valor, this.qtdDisponivel, this.descricao, categoria, caracteristicas, usuario);
    }

    public Object procuraCategoria(EntityManager entityManager) {
        Query query = entityManager.createQuery("select c from Categoria c where c.nomeCategoria=:value");
        query.setParameter("value", this.categoria);
        return query.getSingleResult();

    }

    public boolean produtoEhUnico(EntityManager entityManager, Usuario usuario, ProdutoRequest request,
                                  Produto produtoNovo) throws ProdutoDuplicadoException {
        Query query = entityManager.createQuery("select p " +
                "from Produto p " +
                "join p.usuario u " +
                "where p.nome =:nome " +
                "and u.email =:username")
                .setParameter("nome", request.getNome())
                .setParameter("username", usuario.getUsername());
        List<?> listaConsulta = query.getResultList();
        System.out.println(listaConsulta.isEmpty());
        if(!listaConsulta.isEmpty()) {
            for (Object o : listaConsulta) {
                Produto produto = (Produto) o;
                if (produto.getProdutoCaracteristica().equals(produtoNovo.getProdutoCaracteristica())) {
                    throw new ProdutoDuplicadoException("Esse produto já foi cadastrado por você.");
                }
            }
            System.out.println("Retornando true");
            return true;
        }
        System.out.println("Retornando true");
        return true;
    }
}

