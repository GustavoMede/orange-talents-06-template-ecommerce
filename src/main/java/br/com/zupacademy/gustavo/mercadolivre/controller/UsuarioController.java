package br.com.zupacademy.gustavo.mercadolivre.controller;


import br.com.zupacademy.gustavo.mercadolivre.dto.UsuarioRequest;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid UsuarioRequest form){

        Usuario usuario = form.converte(form);

        entityManager.persist(usuario);
        return ResponseEntity.ok().build();
    }
}
