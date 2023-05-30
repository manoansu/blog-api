package pt.amane.blogapi.blog.utilidade;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class ConversorSlug {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\a]");

    public ConversorSlug(){}

    public static String conversorJuntoComCodigo(String texto) {
        return converter(texto) + "-" + GeradorCodigo.gerar();
    }

    private static String converter(String texto) {

        if(texto == null) {
            throw new IllegalArgumentException("String está nula");
        }

        if (texto.isEmpty()) {
            throw new IllegalArgumentException("String está vazia");
        }

        String noWiteSpace = WHITESPACE.matcher(texto).replaceAll("-");
        String normalized = Normalizer.normalize(noWiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");

        return slug.toLowerCase(Locale.ENGLISH);
    }
}
