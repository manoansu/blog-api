package pt.amane.blogapi.ecomerce;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemCarrinhoCompra {

    private final Produto produto;

    private  int quantidade;

    public ItemCarrinhoCompra(Produto produto, int quantidade) {
        Objects.requireNonNull(produto);
        Objects.requireNonNull(quantidade);
        validarQuantidade(quantidade);
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorTotal() {
        return this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
    }

    public void adicionarQuantidade(int quantidade) {
        validarQuantidade(quantidade);
        this.quantidade += quantidade;
    }

    public void subtrairQuantidade(int quantidade) {
        validarQuantidade(quantidade);
        if (quantidade >= this.quantidade) {
            throw new IllegalArgumentException();
        }

        this.quantidade -= quantidade;
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
