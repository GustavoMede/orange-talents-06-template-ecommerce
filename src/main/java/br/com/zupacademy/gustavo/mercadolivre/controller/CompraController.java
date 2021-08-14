package br.com.zupacademy.gustavo.mercadolivre.controller;

import br.com.zupacademy.gustavo.mercadolivre.dto.CompraRequest;
import br.com.zupacademy.gustavo.mercadolivre.dto.PerguntaDto;
import br.com.zupacademy.gustavo.mercadolivre.enums.Gateway;
import br.com.zupacademy.gustavo.mercadolivre.model.Compra;
import br.com.zupacademy.gustavo.mercadolivre.model.Produto;
import br.com.zupacademy.gustavo.mercadolivre.model.Usuario;
import br.com.zupacademy.gustavo.mercadolivre.utils.EnviaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    EnviaEmail enviaEmail;

    @PostMapping("/produto/{id}")
    @Transactional
    public ResponseEntity<?> cadastraCompra(@PathVariable Long id, @RequestBody @Valid CompraRequest compraRequest,
                                 @AuthenticationPrincipal Usuario usuario) {
        if(usuario == null){
            return new ResponseEntity<>("Você precisa estar logado antes de realizar essa ação.", HttpStatus.FORBIDDEN);
        }

        Produto produto = entityManager.find(Produto.class, id);
        if(produto == null) {
            return new ResponseEntity<>("Produto não encontrado.", HttpStatus.NOT_FOUND);
        }

        if(produto.getQtdDisponivel() >= compraRequest.getQuantidade()){
            Produto produtoEstoqueAtualizado = produto.abateEstoque(compraRequest.getQuantidade());
            entityManager.persist(produtoEstoqueAtualizado);

            Compra compra = compraRequest.converte(produto, usuario);
            entityManager.persist(compra);

            enviaEmail.enviaEmailCompra("Nova compra registrada para seu produto "+ produto.getNome());

            return ResponseEntity.status(HttpStatus.FOUND).body(Gateway.retornaUrl(compraRequest.getGateway(), compra.getUUID()));
        }

        return new ResponseEntity<>("Produto com quantidade insuficiente ou esgotado.", HttpStatus.BAD_REQUEST);
    }
}
