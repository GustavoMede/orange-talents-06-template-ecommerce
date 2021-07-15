package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.annotation.CampoDuplicado;
import br.com.zupacademy.gustavo.mercadolivre.enums.TipoCaracteristica;
import br.com.zupacademy.gustavo.mercadolivre.model.Caracteristica;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class NovaCaracteristicaRequest {

    @NotBlank
    @CampoDuplicado(domainClass = Caracteristica.class, fieldName = "nome")
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoCaracteristica tipoCaracteristica;
    private String descricao;
    private String caracteristicaMae;

    public TipoCaracteristica getTipoCaracteristica() {
        return tipoCaracteristica;
    }


    public String getNome() {
        return nome;
    }

    public String getCaracteristicaMae() {
        return caracteristicaMae;
    }
}
