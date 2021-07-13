package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.CategoriaRequest;
import br.com.zupacademy.gustavo.mercadolivre.enums.TipoCategoria;
import br.com.zupacademy.gustavo.mercadolivre.model.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid CategoriaRequest request){
        TipoCategoria tipoCategoria = request.getTipoCategoria();
        if(tipoCategoria == TipoCategoria.CATEGORIA){

            Categoria categoria = request.converteCategoria();

            entityManager.persist(categoria);

            return ResponseEntity.ok().build();
        }
        if(tipoCategoria == TipoCategoria.SUBCATEGORIA){
            if(request.getNomeSubcategoria() == null || request.getNomeCategoriaMae() == null){
                return ResponseEntity.badRequest().build();
            }
            Query query = entityManager.createQuery("select c from Categoria c where nomeCategoria=:value");
            query.setParameter("value", request.getNomeCategoriaMae());
            List<?> resultadoConsulta = query.getResultList();
            if(resultadoConsulta.isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            Object objeto = query.getSingleResult();
            var categoria = (Categoria) objeto;

            Categoria subcategoria = request.converteSubcategoria(categoria);

            entityManager.persist(subcategoria);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
