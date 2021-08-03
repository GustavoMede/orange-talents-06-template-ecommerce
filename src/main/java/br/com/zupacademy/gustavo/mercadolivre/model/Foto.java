package br.com.zupacademy.gustavo.mercadolivre.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Foto {

    @NotBlank
    private String url;
    @NotNull
    private Long produtoId;

    public Foto(Long produtoId, String url) {
        this.produtoId = produtoId;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
