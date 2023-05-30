package pt.amane.blogapi.blog.negocio;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class GerenciadorEnvioEmail {

    public void enviarEmail(Mensagem mensagem) {

        try {
            Email email =  new SimpleEmail();
            email.setHostName("stmp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("manoansu", ""));
            email.setSSLOnConnect(true);
            email.setFrom("manoanau@gmail.com");
            email.setSubject(mensagem.getAssunto());
            email.setMsg(mensagem.getConteudo());
            email.addTo(mensagem.getAssunto());
            email.send();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
