package br.com.zupacademy.gustavo.mercadolivre.model;

import br.com.zupacademy.gustavo.mercadolivre.enums.Gateway;
import br.com.zupacademy.gustavo.mercadolivre.enums.StatusCompra;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String UUID;
    @Enumerated(EnumType.STRING) @NotNull
    private Gateway gateway;
    @ManyToOne @NotNull
    private Produto produto;
    @NotNull @Positive
    private Integer quantidade;
    @ManyToOne @NotNull
    private Usuario usuario;
    @NotNull @Positive
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra = StatusCompra.INICIADO;

    public Compra(@NotNull Gateway gateway, @NotNull Produto produto, @NotNull @Positive Integer quantidade,
                  @NotNull Usuario usuario) {
        this.gateway = gateway;
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        Assert.state(produto != null, "Erro: Produto não associado à compra, por favor, tente novamente mais tarde.");
        this.valor = produto.getValor();
    }

    public String getUUID() {
        return UUID;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getUsuario() {
        return usuario.getUsername();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }
}
