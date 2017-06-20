package models.tn;

/**
 * Created by amendez on 09/05/17.
 */
public class NoticiasDeInteres {
    private Integer id;
    private noticia noticia;
    private TipoNoticiaInteres tipoNoticiaInteres;
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public NoticiasDeInteres setId(Integer id) {
        this.id = id;
        return this;
    }

    public noticia getNoticia() {
        return noticia;
    }

    public NoticiasDeInteres setNoticia(noticia noticia) {
        this.noticia = noticia;
        return this;
    }

    public TipoNoticiaInteres getTipoNoticiaInteres() {
        return tipoNoticiaInteres;
    }

    public NoticiasDeInteres setTipoNoticiaInteres(TipoNoticiaInteres tipoNoticiaInteres) {
        this.tipoNoticiaInteres = tipoNoticiaInteres;
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public NoticiasDeInteres setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

}
