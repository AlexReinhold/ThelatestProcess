package models.tn;

import java.util.Date;

public class Articuloeditado {
    private int id;
    private Usuario usuario;
    private Articulo articulo;
    private String antesEdicion;
    private String despuesEdicion;
    private Date fechaEdicion;

    public Articuloeditado() {

    }

    public Articuloeditado(int id, Usuario usuario, Articulo articulo, String antesEdicion,
                           String despuesEdicion, Date fechaEdicion) {
        this.id = id;
        this.usuario = usuario;
        this.articulo = articulo;
        this.antesEdicion = antesEdicion;
        this.despuesEdicion = despuesEdicion;
        this.fechaEdicion = fechaEdicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getAntesEdicion() {
        return antesEdicion;
    }

    public void setAntesEdicion(String antesEdicion) {
        this.antesEdicion = antesEdicion;
    }

    public String getDespuesEdicion() {
        return despuesEdicion;
    }

    public void setDespuesEdicion(String despuesEdicion) {
        this.despuesEdicion = despuesEdicion;
    }

    public Date getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Date fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }
}