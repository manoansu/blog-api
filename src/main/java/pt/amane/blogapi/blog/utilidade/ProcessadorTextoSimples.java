package pt.amane.blogapi.blog.utilidade;

import pt.amane.blogapi.blog.negocio.ProcessadorTexto;

public class ProcessadorTextoSimples implements ProcessadorTexto {

    @Override
    public int quantidadePalavras(String texto) {
        if (texto == null || texto.isBlank()) {
            return 0;
        }
        return texto.split(" ").length;
    }
}
