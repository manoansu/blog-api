package pt.amane.blogapi.blog.negocio;

import pt.amane.blogapi.blog.modelo.Editor;
import pt.amane.blogapi.blog.modelo.Ganhos;
import pt.amane.blogapi.blog.modelo.Post;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculadoraGanhos {

    private final ProcessadorTexto processadorTexto;

    private final BigDecimal bonusPremium;

    public CalculadoraGanhos(ProcessadorTexto processadorTexto, BigDecimal bonusPremium) {
        Objects.requireNonNull(processadorTexto);
        Objects.requireNonNull(bonusPremium);
        this.processadorTexto = processadorTexto;
        this.bonusPremium = bonusPremium;
    }

    public Ganhos calcular(Post post) {
        Objects.requireNonNull(post);
        Editor autor = post.getAuthor();
        Objects.requireNonNull(autor);

        //TODO verificar sepost já não está pago, retornar exception

        BigDecimal valorPagoPorPalavra = autor.getValorPagoPorPalavra();
        int quantidadePalavras = processadorTexto.quantidadePalavras(post.getConteudo());
        BigDecimal totalGanho = valorPagoPorPalavra.multiply(BigDecimal.valueOf(quantidadePalavras));

        if (post.getAuthor().isPremium()) {
            totalGanho = totalGanho.add(bonusPremium);
        }

        return new Ganhos(valorPagoPorPalavra, quantidadePalavras, totalGanho);
    }
}
