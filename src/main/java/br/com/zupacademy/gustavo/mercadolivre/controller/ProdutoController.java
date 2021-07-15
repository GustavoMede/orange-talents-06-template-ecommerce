package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.ProdutoRequest;
import br.com.zupacademy.gustavo.mercadolivre.model.Categoria;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.ProdutoCaracteristica;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String cadastra(@RequestBody @Valid ProdutoRequest request){

        Categoria categoria = (Categoria) request.procuraCategoria(entityManager);

        List<ProdutoCaracteristica> produtoCaracteristica = request.converteProdutoCaracteristica(entityManager);

        Produto produto = request.converte(categoria, produtoCaracteristica);

        entityManager.persist(produto);

        return produto.getClass().toString();
    }
}
