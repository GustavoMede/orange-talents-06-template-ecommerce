package br.com.zupacademy.gustavo.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<FotoProduto> fotos = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<OpiniaoProduto> opinioes;
    @OneToMany(mappedBy = "produto")
    private Set<PerguntaProduto> perguntas;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    @JoinColumn
    private Usuario usuario;


    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @DecimalMin("0.01") BigDecimal valor,
                   @NotNull @Min(0) Long qtdDisponivel, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull Categoria categoria, List<ProdutoCaracteristica> produtoCaracteristica, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        Set<ProdutoCaracteristica> caracteristicasProduto = produtoCaracteristica.stream()
                .map(caracteristica -> caracteristica.converte(this)).collect(Collectors.toSet());
        this.produtoCaracteristica.addAll(caracteristicasProduto);
        this.usuario = usuario;
    }

    public Produto converteFotos(Set<String> fotos, Produto produto){
        Set<FotoProduto> fotosProduto = fotos.stream()
                .map(foto -> new FotoProduto(foto, this)).collect(Collectors.toSet());
        this.fotos.addAll(fotosProduto);
        return produto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getQtdDisponivel() {
        return qtdDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Set<ProdutoCaracteristica> getProdutoCaracteristica() {
        return produtoCaracteristica;
    }

    public Set<FotoProduto> getFotos() {
        return fotos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<OpiniaoProduto> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaProduto> getPerguntas() {
        return perguntas;
    }

    public List<Integer> carregaNotas(EntityManager entityManager, Produto produto) {
        List<Integer> notas = new ArrayList<>();
        Query query = entityManager.createQuery("Select o.nota from Produto p " +
                "join p.opinioes o " +
                "where p.id=:id").setParameter("id", produto.getId());
        List<?> resultados = query.getResultList();
        resultados.forEach(resultado -> {
            notas.add((Integer) resultado);
        });
        return notas;
    }

    public Double calculaMedia(List<Integer> notas) {
        return notas.stream().mapToDouble(nota -> nota)
                .average()
                .orElse(0.0);
    }
}
