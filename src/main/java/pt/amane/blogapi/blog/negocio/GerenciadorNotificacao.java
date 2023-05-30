package pt.amane.blogapi.blog.negocio;

import pt.amane.blogapi.blog.modelo.Notificacao;

public interface GerenciadorNotificacao {

    void enviar(Notificacao notificação);
}
