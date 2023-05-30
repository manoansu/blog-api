package pt.amane.blogapi.blog.armazenamento;

import pt.amane.blogapi.blog.modelo.Post;

import java.util.List;
import java.util.Optional;

public interface ArmazenamentoPost {

    Post salvar(Post post);

    Optional<Post> encontrarPorId(Long postId);

    void remover(Long postId);

    List<Post> encontrarTodos();
}
