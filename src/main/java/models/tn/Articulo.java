package models.tn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Articulo {
    private int id;
    private String titulo;
    private String contenido;
    private String contenidoHtml;
    private FuentePorArticulo fuentePorArticulo;
    private noticia noticia;
    private String autor;
    private String autorFoto;
    private Timestamp fechaPublicacion;
    private Timestamp fechaEntrada;
    private int removido;
    private String snippet;
    private String tags;
    private String url;
    private List<Multimedia> videos;
    private List<Multimedia> imagenes;

    public Articulo() {
        this.videos = new ArrayList<>();
        this.imagenes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Articulo addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Articulo addTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getContenido() {
        return contenido;
    }

    public Articulo addContenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public String getContenidoHtml() {
        return contenidoHtml;
    }

    public Articulo addContenidoHtml(String contenidoHtml) {
        this.contenidoHtml = contenidoHtml;
        return this;
    }

    public FuentePorArticulo getFuentePorArticulo() {
        return fuentePorArticulo;
    }

    public Articulo addFuentePorArticulo(FuentePorArticulo fuentePorArticulo) {
        this.fuentePorArticulo = fuentePorArticulo;
        return this;
    }

    public noticia getNoticia() {
        return noticia;
    }

    public Articulo addNoticia(noticia noticia) {
        this.noticia = noticia;
        return this;
    }

    public String getAutor() {
        return autor;
    }

    public Articulo addAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public String getAutorFoto() {
        return autorFoto;
    }

    public Articulo addAutorFoto(String autorFoto) {
        this.autorFoto = autorFoto;
        return this;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Articulo addFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public Timestamp getFechaEntrada() {
        return fechaEntrada;
    }

    public Articulo addFechaEntrada(Timestamp fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public int getRemovido() {
        return removido;
    }

    public Articulo addRemovido(int removido) {
        this.removido = removido;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public Articulo addSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Articulo addTags(String tags) {
        this.tags = tags;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Articulo addUrl(String url) {
        this.url = url;
        return this;
    }

    public List<Multimedia> getVideos() {
        return videos;
    }

    public Articulo addVideos(List<Multimedia> videos) {
        this.videos = videos;
        return this;
    }

    public List<Multimedia> getImagenes() {
        return imagenes;
    }

    public Articulo addImagenes(List<Multimedia> imagenes) {
        this.imagenes = imagenes;
        return this;
    }
}