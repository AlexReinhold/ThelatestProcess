package models.tl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Story {
    private int id;
    private int vistas;
    private int compartidos;
    private Category categoria;
    private int posicion;
    private String titulo;
    private int idExterno;
    private String slug;
    private Timestamp deadLine;
    private String tags;
    private Timestamp fechaPublicacion;
    private Timestamp ultimaActualizacion;
    private int numeroFuentes;
    private List<News> articulos;
    private int porcentajeBusqueda;

    public Story() {
        articulos = new ArrayList<News>();
    }

    public int getId() {
        return id;
    }

    public Story addId(int id) {
        this.id = id;
        return this;
    }

    public int getVistas() {
        return vistas;
    }

    public Story addVistas(int vistas) {
        this.vistas = vistas;
        return this;
    }

    public int getCompartidos() {
        return compartidos;
    }

    public Story addCompartidos(int compartidos) {
        this.compartidos = compartidos;
        return this;
    }

    public Category getCategoria() {
        return categoria;
    }

    public Story addCategoria(Category categoria) {
        this.categoria = categoria;
        return this;
    }

    public int getPosicion() {
        return posicion;
    }

    public Story addPosicion(int posicion) {
        this.posicion = posicion;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Story addTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public Story addIdExterno(int idExterno) {
        this.idExterno = idExterno;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Story addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public Story addDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Story addTags(String tags) {
        this.tags = tags;
        return this;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Story addFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public Timestamp getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public Story addUltimaActualizacion(Timestamp ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
        return this;
    }

    public int getNumeroFuentes() {
        return numeroFuentes;
    }

    public Story addNumeroFuentes(int numeroFuentes) {
        this.numeroFuentes = numeroFuentes;
        return this;
    }

    public List<News> getArticulos() {
        return articulos;
    }

    public Story addArticulos(List<News> articulos) {
        this.articulos = articulos;
        return this;
    }

    public int getPorcentajeBusqueda() {
        return porcentajeBusqueda;
    }

    public Story addPorcentajeBusqueda(int porcentajeBusqueda) {
        this.porcentajeBusqueda = porcentajeBusqueda;
        return this;
    }
}

