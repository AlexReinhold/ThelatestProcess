package models.tl;

/**
 * Created by nlperez on 4/18/17.
 */
public class ArticuloNoProcesado {
    private int id;
    private Articulo articulo;
    private boolean fechaSync;
    private boolean tagsSync;
    private boolean numeroFuentesSync;

    public ArticuloNoProcesado() {
    }

    public int getId() {
        return id;
    }

    public ArticuloNoProcesado addId(int id) {
        this.id = id;
        return this;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public ArticuloNoProcesado addArticulo(Articulo articulo) {
        this.articulo = articulo;
        return this;
    }

    public boolean isFechaSync() {
        return fechaSync;
    }

    public ArticuloNoProcesado addFechaSync(boolean fechaSync) {
        this.fechaSync = fechaSync;
        return this;
    }

    public boolean isTagsSync() {
        return tagsSync;
    }

    public ArticuloNoProcesado addTagsSync(boolean tagsSync) {
        this.tagsSync = tagsSync;
        return this;
    }

    public boolean isNumeroFuentesSync() {
        return numeroFuentesSync;
    }

    public ArticuloNoProcesado addNumeroFuentesSync(boolean numeroFuentesSync) {
        this.numeroFuentesSync = numeroFuentesSync;
        return this;
    }
}
