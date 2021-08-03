package br.com.zupacademy.gustavo.mercadolivre.dto;

import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

public class FotoRequest {

    @Size(min = 1)
    @NotNull
    List<MultipartFile> fotos;

    public void setFotos(List<MultipartFile> fotos) {
        this.fotos = fotos;
    }

    public boolean verificaDono(EntityManager entityManager, String username, Long id) {
        Query query = entityManager.createQuery("select p from Produto p " +
                "join p.usuario u " +
                "where p.id =:id " +
                "and u.email =:username");
        query.setParameter("id", id);
        query.setParameter("username", username);
        List<?> consulta = query.getResultList();
        return !consulta.isEmpty();
    }

    public Produto encontraProduto(EntityManager entityManager, Long id) {
        Optional<Produto> produto = Optional.ofNullable(entityManager.find(Produto.class, id));
        return produto.orElse(null);
    }

    public List<MultipartFile> getFotos() {
        return fotos;
    }
}
