package pt.amane.blogapi.blog.modelo;

import java.util.Objects;

public class Post {

    private Long id;
    private String titulo;
    private String conteudo;

    private Editor author;

    private String slug;

    private Ganhos ganhos;

    private boolean pago;
    private boolean publicado;

    public Post(){}

    public Post(String titulo, String conteudo, Editor author, String slug, Ganhos ganhos, boolean pago, boolean publicado) {
        this(null, titulo, conteudo, author, slug, ganhos, pago, publicado);
    }

    public Post(Long id, String titulo, String conteudo, Editor author, String slug, Ganhos ganhos, boolean pago, boolean publicado) {
        Objects.requireNonNull(titulo);
        Objects.requireNonNull(author);
        Objects.requireNonNull(conteudo);
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.author = author;
        this.slug = slug;
        this.ganhos = ganhos;
        this.pago = pago;
        this.publicado = publicado;
    }

    public void publicar() {
        this.publicado = true;
    }

    public void marcarCampoPago() {
        this.pago = true;
    }
    public void atualizarComDados(Post post) {
        Objects.requireNonNull(post);
        this.titulo = post.titulo;
        this.conteudo = post.conteudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Editor getAuthor() {
        return author;
    }

    public void setAuthor(Editor author) {
        this.author = author;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Ganhos getGanhos() {
        return ganhos;
    }

    public void setGanhos(Ganhos ganhos) {
        this.ganhos = ganhos;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
