package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.annotation.CampoDuplicado;
import br.com.zupacademy.gustavo.mercadolivre.enums.TipoCategoria;
import br.com.zupacademy.gustavo.mercadolivre.model.Categoria;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CategoriaRequest {
    @CampoDuplicado(domainClass = Categoria.class, fieldName = "nomeCategoria")
    private String nomeCategoria;
    @CampoDuplicado(domainClass = Categoria.class, fieldName = "nomeCategoria")
    private String nomeSubcategoria;
    private String nomeCategoriaMae;
    @Enumerated(EnumType.STRING)
    private TipoCategoria tipoCategoria;

    public CategoriaRequest(String nomeCategoria, String nomeSubcategoria, String nomeCategoriaMae, TipoCategoria tipoCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.nomeSubcategoria = nomeSubcategoria;
        this.nomeCategoriaMae = nomeCategoriaMae;
        this.tipoCategoria = tipoCategoria;
    }

    public TipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public String getNomeCategoriaMae() {
        return nomeCategoriaMae;
    }

    public String getNomeSubcategoria() {
        return nomeSubcategoria;
    }

    public Categoria converteCategoria(){
        return new Categoria(this.nomeCategoria);
    }

    public Categoria converteSubcategoria(Categoria categoria){
        return new Categoria(this.nomeSubcategoria, categoria);
    }
}
