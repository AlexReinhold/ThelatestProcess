package models.tl;

/**
 * Created by amendez on 10/05/17.
 */
public class ReaccionArticulos {
    private Integer id;
    private Articulo articulo;
    private Usuario usuario;
    private TipoReaccion tipoReaccion;

    public Integer getId() {
        return id;
    }

    public ReaccionArticulos setId(Integer id) {
        this.id = id;
        return this;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public ReaccionArticulos setArticulo(Articulo articulo) {
        this.articulo = articulo;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ReaccionArticulos setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public TipoReaccion getTipoReaccion() {
        return tipoReaccion;
    }

    public ReaccionArticulos setTipoReaccion(TipoReaccion tipoReaccion) {
        this.tipoReaccion = tipoReaccion;
        return this;
    }
}
