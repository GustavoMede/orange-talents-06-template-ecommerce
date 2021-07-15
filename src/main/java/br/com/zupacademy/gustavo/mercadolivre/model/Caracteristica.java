package br.com.zupacademy.gustavo.mercadolivre.model;

import br.com.zupacademy.gustavo.mercadolivre.annotation.CampoDuplicado;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @ManyToOne
    private Caracteristica outraCaracteristica;

    @Deprecated
    public Caracteristica() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Caracteristica(@NotBlank String nome) {
        this.nome = nome;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Caracteristica(@NotBlank String nome, Caracteristica caracteristica) {
        this.nome = nome;
        this.outraCaracteristica = caracteristica;
    }
}
