package br.com.zupacademy.gustavo.mercadolivre.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Long qtdDisponivel;
    private String descricao;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<ProdutoCaracteristica> produtoCaracteristica = new HashSet<>();
    @ManyToOne
    private Categoria categoria;

    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @DecimalMin("0.01") BigDecimal valor,
                   @NotNull @Min(0) Long qtdDisponivel, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull Categoria categoria, List<ProdutoCaracteristica> produtoCaracteristica) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        Set<ProdutoCaracteristica> caracteristicasProduto = produtoCaracteristica.stream()
                .map(caracteristica -> caracteristica.converte(this)).collect(Collectors.toSet());
        this.produtoCaracteristica.addAll(caracteristicasProduto);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", qtdDisponivel=" + qtdDisponivel +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", produtoCaracteristica=" + produtoCaracteristica +
                ", categoria=" + categoria +
                '}';
    }
}
