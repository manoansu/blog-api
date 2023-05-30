package pt.amane.blogapi.ecomerce;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    public Produto(Long id, String nome, String descricao, BigDecimal valor) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(nome);
        Objects.requireNonNull(descricao);
        Objects.requireNonNull(valor);
        validarValor(valor);
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    private void validarValor(BigDecimal valor) {

        if (valor.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(valor, produto.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, valor);
    }
}
