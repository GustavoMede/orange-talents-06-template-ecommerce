package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.ProdutoRequest;
import br.com.zupacademy.gustavo.mercadolivre.exception.ProdutoDuplicadoException;
import br.com.zupacademy.gustavo.mercadolivre.model.*;
import br.com.zupacademy.gustavo.mercadolivre.security.AutenticacaoManager;
import br.com.zupacademy.gustavo.mercadolivre.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TokenManager tokenManager;

    @Autowired
    AutenticacaoManager autenticacaoManager;

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
}
