package pt.amane.blogapi.ecomerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarrinhoCompra {

    private final Cliente cliente;

    private final List<ItemCarrinhoCompra> itens;

    public CarrinhoCompra(Cliente cliente) {
        this(cliente, new ArrayList<>());
    }

    public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
        Objects.requireNonNull(cliente);
        Objects.requireNonNull(itens);
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
    }

    /**
     * TODO deve retornar uma nova lista para que a antiga não seja alterada
     * @return
     */
    public List<ItemCarrinhoCompra> getItens() {
        return new ArrayList<>(itens);
    }

    /**
     * TODO parâmetros não podem ser nulos, deve retornar uma exception
     * TODO quantidade não pode ser menor que 1
     * TODO deve incrementar a quantidade caso o produto já exista
     * @param produto
     * @param quantidade
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        Objects.requireNonNull(produto);
        validarQuantidade(quantidade);

        encontrarItemPeloProduto(produto)
                .ifPresentOrElse(item -> item.adicionarQuantidade(quantidade), () -> adicionarNovoItem(produto, quantidade));
    }

    /**
     * TODO parâmetro não pode ser nulo, deve retornar uma exception
     * TODO caso o produto não exista, deve retornar uma exception
     * TODO deve remover o produto independente da quantidade
     * @param produto
     */
    public void removerProduto(Produto produto) {
        Objects.requireNonNull(produto);
        ItemCarrinhoCompra item = encontrarItemPeloProduto(produto).orElseThrow(RuntimeException::new);
        this.itens.remove(item);
    }

    private void adicionarNovoItem(Produto produto, int quantidade) {
        this.itens.add(new ItemCarrinhoCompra(produto, quantidade));
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private Optional<ItemCarrinhoCompra> encontrarItemPeloProduto(Produto produto) {
        return this.itens.stream()
                .filter(item -> item.getProduto().equals(produto))
                .findFirst();
    }
}
