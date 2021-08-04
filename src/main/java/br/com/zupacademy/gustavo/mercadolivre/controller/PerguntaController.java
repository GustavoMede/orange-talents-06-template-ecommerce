package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.PerguntaRequestDto;
import br.com.zupacademy.gustavo.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import br.com.zupacademy.gustavo.mercadolivre.utils.EnviaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    EnviaEmail enviaEmail;

    @PostMapping("/produto/{id}")
    @Transactional
    public ResponseEntity<?> cadastra(@PathVariable Long id, @RequestBody @Valid PerguntaRequestDto request,
                                      @AuthenticationPrincipal Usuario usuario){

        Produto produto = request.encontraProduto(entityManager, id);
        if(produto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        PerguntaProduto perguntaProduto = request.converte(produto, usuario);

        entityManager.persist(perguntaProduto);

        enviaEmail.envia(perguntaProduto.converteDto());

        return ResponseEntity.ok().build();
    }
}
