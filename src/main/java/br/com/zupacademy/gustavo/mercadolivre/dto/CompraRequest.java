package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.enums.Gateway;
import br.com.zupacademy.gustavo.mercadolivre.model.Compra;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gateway gateway;
    @NotNull @Positive
    private Integer quantidade;

    public CompraRequest(Gateway gateway, Integer quantidade) {
        this.gateway = gateway;
        this.quantidade = quantidade;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Compra converte(Produto produto, Usuario usuario) {
        return new Compra(gateway, produto, quantidade, usuario);
    }
}
