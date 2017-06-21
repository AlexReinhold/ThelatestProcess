package models.tl;

public class Multimedia {
    private int id;
    private News articulo;
    private String url;
    private String urlOriginal;
    private int tipoArchivo;
    private int anchura;
    private int altura;

    public Multimedia() {
    }

    public int getId() {
        return id;
    }

    public Multimedia addId(int id) {
        this.id = id;
        return this;
    }

    public News getArticulo() {
        return articulo;
    }

    public Multimedia addArticulo(News articulo) {
        this.articulo = articulo;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Multimedia addUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public Multimedia addUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
        return this;
    }

    public int getTipoArchivo() {
        return tipoArchivo;
    }

    public Multimedia addTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
        return this;
    }

    public int getAnchura() {
        return anchura;
    }

    public Multimedia addAnchura(int anchura) {
        this.anchura = anchura;
        return this;
    }

    public int getAltura() {
        return altura;
    }

    public Multimedia addAltura(int altura) {
        this.altura = altura;
        return this;
    }
}
