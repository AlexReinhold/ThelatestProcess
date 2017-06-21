package models.tl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class News {
    private int id;
    private String titulo;
    private String contenido;
    private String contenidoHtml;
    private Story noticia;
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

    public News() {
        this.videos = new ArrayList<>();
        this.imagenes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public News addId(int id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public News addTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getContenido() {
        return contenido;
    }

    public News addContenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public String getContenidoHtml() {
        return contenidoHtml;
    }

    public News addContenidoHtml(String contenidoHtml) {
        this.contenidoHtml = contenidoHtml;
        return this;
    }

    public Story getNoticia() {
        return noticia;
    }

    public News addNoticia(Story noticia) {
        this.noticia = noticia;
        return this;
    }

    public String getAutor() {
        return autor;
    }

    public News addAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public String getAutorFoto() {
        return autorFoto;
    }

    public News addAutorFoto(String autorFoto) {
        this.autorFoto = autorFoto;
        return this;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public News addFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public Timestamp getFechaEntrada() {
        return fechaEntrada;
    }

    public News addFechaEntrada(Timestamp fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public int getRemovido() {
        return removido;
    }

    public News addRemovido(int removido) {
        this.removido = removido;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public News addSnippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public News addTags(String tags) {
        this.tags = tags;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public News addUrl(String url) {
        this.url = url;
        return this;
    }

    public List<Multimedia> getVideos() {
        return videos;
    }

    public News addVideos(List<Multimedia> videos) {
        this.videos = videos;
        return this;
    }

    public List<Multimedia> getImagenes() {
        return imagenes;
    }

    public News addImagenes(List<Multimedia> imagenes) {
        this.imagenes = imagenes;
        return this;
    }
}