package models.tl;

import java.util.Date;

public class ArticuloRemovido {
    private int id;
    private Usuario usuario;
    private Articulo articulo;
    private String motivo;
    private Date fechaRemovido;

    public ArticuloRemovido() {

    }

    public ArticuloRemovido(int id, Usuario usuario, Articulo articulo, String motivo,
                            Date fechaRemovido) {
        this.id = id;
        this.usuario = usuario;
        this.articulo = articulo;
        this.motivo = motivo;
        this.fechaRemovido = fechaRemovido;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaRemovido() {
        return fechaRemovido;
    }

    public void setFechaRemovido(Date fechaRemovido) {
        this.fechaRemovido = fechaRemovido;
    }
}