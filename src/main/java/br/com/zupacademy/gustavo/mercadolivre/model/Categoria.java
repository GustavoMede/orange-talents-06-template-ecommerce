package br.com.zupacademy.gustavo.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nomeCategoria;

    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(){
    }

    public Categoria(@NotBlank String nome) {
        this.nomeCategoria = nome;
    }

    public Categoria(@NotBlank String nome, Categoria categoria){
        this.nomeCategoria = nome;
        this.categoriaMae = categoria;
    }

}
