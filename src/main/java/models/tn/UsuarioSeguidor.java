package models.tn;

/**
 * Created by amendez on 08/05/17.
 */
public class UsuarioSeguidor {
    private Integer id;
    private Usuario usuario;
    private Usuario seguidor;

    public Integer getId() {
        return id;
    }

    public UsuarioSeguidor setId(Integer id) {
        this.id = id;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioSeguidor setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Usuario getSeguidor() {
        return seguidor;
    }

    public UsuarioSeguidor setSeguidor(Usuario seguidor) {
        this.seguidor = seguidor;
        return this;
    }
}
