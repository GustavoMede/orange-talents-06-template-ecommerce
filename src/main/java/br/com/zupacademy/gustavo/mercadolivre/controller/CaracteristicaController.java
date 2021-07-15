package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.NovaCaracteristicaRequest;
import br.com.zupacademy.gustavo.mercadolivre.enums.TipoCaracteristica;
import br.com.zupacademy.gustavo.mercadolivre.model.Caracteristica;
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

@RestController
@RequestMapping("/caracteristica")
public class CaracteristicaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovaCaracteristicaRequest request){
        TipoCaracteristica tipoCaracteristica = request.getTipoCaracteristica();
        if(tipoCaracteristica == TipoCaracteristica.PRINCIPAL){
            Caracteristica caracteristica = new Caracteristica(request.getNome());

            entityManager.persist(caracteristica);

            return ResponseEntity.ok().build();
        }
        if(tipoCaracteristica == TipoCaracteristica.OUTRAS){
            Query query = entityManager.createQuery("select c from Caracteristica c where c.nome=:value");
            query.setParameter("value", request.getCaracteristicaMae());
            Object object = query.getSingleResult();
            var objetoCaracteristica = (Caracteristica) object;

            Caracteristica caracteristica = new Caracteristica(request.getNome(), objetoCaracteristica);

            entityManager.persist(caracteristica);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
