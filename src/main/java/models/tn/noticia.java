package models.tn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class noticia {
    private int id;
    private int vistas;
    private int compartidos;
    private Categoria categoria;
    private int posicion;
    private String titulo;
    private int idExterno;
    private String slug;
    private Timestamp deadLine;
    private String tags;
    private Timestamp fechaPublicacion;
    private Timestamp ultimaActualizacion;
    private int numeroFuentes;
    private List<Articulo> articulos;
    private int porcentajeBusqueda;

    public noticia() {
        articulos = new ArrayList<Articulo>();
    }

    public int getId() {
        return id;
    }

    public noticia addId(int id) {
        this.id = id;
        return this;
    }

    public int getVistas() {
        return vistas;
    }

    public noticia addVistas(int vistas) {
        this.vistas = vistas;
        return this;
    }

    public int getCompartidos() {
        return compartidos;
    }

    public noticia addCompartidos(int compartidos) {
        this.compartidos = compartidos;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public noticia addCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public int getPosicion() {
        return posicion;
    }

    public noticia addPosicion(int posicion) {
        this.posicion = posicion;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public noticia addTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public noticia addIdExterno(int idExterno) {
        this.idExterno = idExterno;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public noticia addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }

    public noticia addDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public noticia addTags(String tags) {
        this.tags = tags;
        return this;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public noticia addFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public Timestamp getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public noticia addUltimaActualizacion(Timestamp ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
        return this;
    }

    public int getNumeroFuentes() {
        return numeroFuentes;
    }

    public noticia addNumeroFuentes(int numeroFuentes) {
        this.numeroFuentes = numeroFuentes;
        return this;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public noticia addArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
        return this;
    }

    public int getPorcentajeBusqueda() {
        return porcentajeBusqueda;
    }

    public noticia addPorcentajeBusqueda(int porcentajeBusqueda) {
        this.porcentajeBusqueda = porcentajeBusqueda;
        return this;
    }
}

