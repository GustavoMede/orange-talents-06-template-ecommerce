package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.OpiniaoRequest;
import br.com.zupacademy.gustavo.mercadolivre.dto.ProdutoDto;
import br.com.zupacademy.gustavo.mercadolivre.dto.ProdutoRequest;
import br.com.zupacademy.gustavo.mercadolivre.exception.ProdutoDuplicadoException;
import br.com.zupacademy.gustavo.mercadolivre.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuario) throws ProdutoDuplicadoException {
        if(usuario == null){
            return new ResponseEntity<>("Unauthorized", HttpStatus.FORBIDDEN);
        }

        Categoria categoria = (Categoria) request.procuraCategoria(entityManager);
        List<ProdutoCaracteristica> produtoCaracteristica = request.converteProdutoCaracteristica(entityManager);
        Produto produto = request.converte(categoria, produtoCaracteristica, usuario);

        boolean produtoRepetido = request.produtoEhUnico(entityManager, usuario, request, produto);

        entityManager.persist(produto);

        return ResponseEntity.ok(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> apresentaProduto(@PathVariable Long id){
        Produto produto = entityManager.find(Produto.class, id);
        if(produto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O produto não foi encontrado.");
        }

        List<Integer> notas = produto.carregaNotas(entityManager, produto);

        Double media = produto.calculaMedia(notas);

        ProdutoDto produtoDto = new ProdutoDto(produto, notas.size(), media);

        return ResponseEntity.ok(produtoDto);
    }
}
