package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.OpiniaoRequest;
import br.com.zupacademy.gustavo.mercadolivre.model.OpiniaoProduto;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/opiniao")
public class OpiniaoController {

    @Autowired
    EntityManager entityManager;

    @PostMapping("/produto/{id}")
    @Transactional
    public ResponseEntity<?> cadastra(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest request,
                                             @AuthenticationPrincipal Usuario usuario){

        Produto produto = request.encontraProduto(entityManager, id);

        OpiniaoProduto opiniaoProduto = request.converte(produto, usuario);

        entityManager.persist(opiniaoProduto);

        return ResponseEntity.ok().build();
    }
}
