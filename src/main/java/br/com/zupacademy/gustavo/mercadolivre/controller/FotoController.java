package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.FotoRequest;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import br.com.zupacademy.gustavo.mercadolivre.utils.UploaderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/foto")
public class FotoController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UploaderFake uploaderFake;

    @Transactional
    @PostMapping("/produto/{id}/imagem")
    public ResponseEntity<?> cadastra(@PathVariable Long id, @Valid FotoRequest request,
                                      @AuthenticationPrincipal Usuario usuario) {

        if (usuario == null) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }

        boolean ehDoUsuario = request.verificaDono(entityManager, usuario.getUsername(), id);
        if (!ehDoUsuario) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(request.getFotos());

        Produto produto = request.encontraProduto(entityManager, id);

        Produto produtoPersistido = produto.converte(links, produto);

        entityManager.persist(produtoPersistido);

        return ResponseEntity.ok(produtoPersistido);
    }
}
