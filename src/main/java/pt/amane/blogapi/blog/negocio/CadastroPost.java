package pt.amane.blogapi.blog.negocio;

import pt.amane.blogapi.blog.armazenamento.ArmazenamentoPost;
import pt.amane.blogapi.blog.exception.PostNaoEncontradoException;
import pt.amane.blogapi.blog.exception.RegraNegocioException;
import pt.amane.blogapi.blog.modelo.Notificacao;
import pt.amane.blogapi.blog.modelo.Post;
import pt.amane.blogapi.blog.utilidade.ConversorSlug;

import java.time.OffsetDateTime;
import java.util.Objects;

public class CadastroPost {

    //Não temos essa implementação, pois nesse projeto não sabemos nem qual framework vamos usar
    private final ArmazenamentoPost armazenamentoPost;

    private final CalculadoraGanhos calculadoraGanhos;

    private final GerenciadorNotificacao gerenciadorNotificacao;


    public CadastroPost(ArmazenamentoPost armazenamentoPost, CalculadoraGanhos calculadoraGanhos, GerenciadorNotificacao gerenciadorNotificacao) {
        this.armazenamentoPost = armazenamentoPost;
        this.calculadoraGanhos = calculadoraGanhos;
        this.gerenciadorNotificacao = gerenciadorNotificacao;
    }

    public Post criar(Post post) {
        Objects.requireNonNull(post);
        post.setSlug(criarSlug(post)); //precisa de mock? pode fazer teste falhar
        post.setGanhos(this.calculadoraGanhos.calcular(post)); //mock
        post = armazenamentoPost.salvar(post);
        enviarNotificacao(post);

        return  post;
    }

    /**
     *  Testar se calculou ganhos? ou apenas se chamou? Testar propriedades que foram setadas?
     *  Testar calculo do ganho apenas no service que calcula
     *  Verificar se ganhos e slug não estão nulos
     *  Apos testar tudo, mover testes para classes respectivas
     *  Cada classe de teste, testa apenas as regras da propria classe testada
     *  Devemos testar o service chama as outras classes ou apenas o resultado
     * @param postAtualizado
     * @return
     */
    public Post editar(Post postAtualizado) {
        Objects.requireNonNull(postAtualizado);

        Post post = this.armazenamentoPost.encontrarPorId(postAtualizado.getId())
                .orElseThrow(PostNaoEncontradoException::new);
        post.atualizarComDados(postAtualizado);

        if (!post.isPago()) {
            post.setGanhos(this.calculadoraGanhos.calcular(post));
        }

        return armazenamentoPost.salvar(post);
    }

    public void remover(Long postId) {
        Objects.requireNonNull(postId);
        Post post = this.armazenamentoPost.encontrarPorId(postId)
                .orElseThrow(PostNaoEncontradoException::new);

        if (post.isPublicado()) {
            throw new RegraNegocioException("Um post publicado não pode ser removido");
        }

        if (post.isPago()) {
            throw new RegraNegocioException("Um post pago não pode ser removido");
        }
        this.armazenamentoPost.remover(postId);
    }

    private void enviarNotificacao(Post post) {

        Notificacao notificacao = new Notificacao(
                OffsetDateTime.now(),
                "Novoo post criado -> " + post.getTitulo() //Precisa testar isso? Construtor estático? refatorar?
        );

        this.gerenciadorNotificacao.enviar(notificacao);
    }

    private String criarSlug(Post post) {
        return ConversorSlug.conversorJuntoComCodigo(post.getTitulo());
    }
}
