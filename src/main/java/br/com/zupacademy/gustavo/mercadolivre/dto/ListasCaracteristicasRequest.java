package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.model.Caracteristica;

import javax.validation.constraints.NotBlank;

public class ListasCaracteristicasRequest {

    @NotBlank
    private String nome;
    private String descricao;

    public Caracteristica converteCaracteristica(){
        return new Caracteristica(this.nome);
    }

    public Caracteristica converteOutraCaracteristica(Caracteristica caracteristica){
        return new Caracteristica(this.nome, caracteristica);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
