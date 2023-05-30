package pt.amane.blogapi.blog.armazenamento;

import pt.amane.blogapi.blog.modelo.Editor;

import java.util.List;
import java.util.Optional;

public interface ArmazenamentoEditor {

    Editor salvar(Editor editor);

    Optional<Editor> encontrarPorId(Long editorId);

    Optional<Editor> encontrarPorEmail(String email);

    Optional<Editor> encontrarPorEmailComIdDiferenteDe(String  email, Long editorId);

    void remover(Long editorId);

    List<Editor> encontrarTodos();

}
