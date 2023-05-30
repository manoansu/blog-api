package pt.amane.blogapi.blog.negocio;

import pt.amane.blogapi.blog.armazenamento.ArmazenamentoEditor;
import pt.amane.blogapi.blog.exception.EditorNaoEncontradoException;
import pt.amane.blogapi.blog.exception.RegraNegocioException;
import pt.amane.blogapi.blog.modelo.Editor;

import java.util.Objects;

public class CadastroEditor {

    private ArmazenamentoEditor armazenamentoEditor;

    private GerenciadorEnvioEmail gerenciadorEnvioEmail;

    public CadastroEditor(ArmazenamentoEditor armazenamentoEditor, GerenciadorEnvioEmail gerenciadorEnvioEmail) {
        this.armazenamentoEditor = armazenamentoEditor;
        this.gerenciadorEnvioEmail = gerenciadorEnvioEmail;
    }

    public Editor criar(Editor editor) {
        Objects.requireNonNull(editor);

        //Verifica se o email do editor ja foi registado na Bd..
        verificarEmail(editor);

        editor = armazenamentoEditor.salvar(editor);
        enviarEmailDeNovoRegisto(editor);

        return editor;
    }

    public Editor editar(Editor editorAtualizado) {
        Objects.requireNonNull(editorAtualizado);

        verificarSeExisteEditorUsandoMesmoEmailComIdDiferente(editorAtualizado);
        Editor editor = buscarEditor(editorAtualizado);
        editor.atualizarComDados(editorAtualizado);

        return armazenamentoEditor.salvar(editor);
    }

    public void remover(Long editorId) {
        Objects.requireNonNull(editorId);
        armazenamentoEditor.encontrarPorId(editorId).orElseThrow(EditorNaoEncontradoException::new);
        armazenamentoEditor.remover(editorId);
    }

    private Editor buscarEditor(Editor editorAtualizado) {
        return  (armazenamentoEditor.encontrarPorId(editorAtualizado.getId())
                .orElseThrow(EditorNaoEncontradoException::new));
    }

    private void verificarSeExisteEditorUsandoMesmoEmailComIdDiferente(Editor editorAtualizado) {

        if (armazenamentoEditor.encontrarPorEmailComIdDiferenteDe(
                editorAtualizado.getEmail(),
                editorAtualizado.getId()).isPresent()) {
            throw new RegraNegocioException("Já existe um editor com esse e-mail " + editorAtualizado.getEmail());

        }
    }

    private void enviarEmailDeNovoRegisto(Editor editor) {
        Mensagem mensagem = new Mensagem(editor.getEmail(), "Novo registo", "Seu registo foi concluído");
        gerenciadorEnvioEmail.enviarEmail(mensagem);
    }

    private void verificarEmail(Editor editor) {
        if (armazenamentoEditor.encontrarPorEmail(editor.getEmail()).isPresent()){
            throw new RegraNegocioException("já existe um editor com esse e-mail " + editor.getEmail());
        }
    }
}
