package models.tl;

public class UnprocessedNews {
    private int id;
    private News articulo;
    private boolean fechaSync;
    private boolean tagsSync;
    private boolean numeroFuentesSync;

    public UnprocessedNews() {
    }

    public int getId() {
        return id;
    }

    public UnprocessedNews addId(int id) {
        this.id = id;
        return this;
    }

    public News getArticulo() {
        return articulo;
    }

    public UnprocessedNews addArticulo(News articulo) {
        this.articulo = articulo;
        return this;
    }

    public boolean isFechaSync() {
        return fechaSync;
    }

    public UnprocessedNews addFechaSync(boolean fechaSync) {
        this.fechaSync = fechaSync;
        return this;
    }

    public boolean isTagsSync() {
        return tagsSync;
    }

    public UnprocessedNews addTagsSync(boolean tagsSync) {
        this.tagsSync = tagsSync;
        return this;
    }

    public boolean isNumeroFuentesSync() {
        return numeroFuentesSync;
    }

    public UnprocessedNews addNumeroFuentesSync(boolean numeroFuentesSync) {
        this.numeroFuentesSync = numeroFuentesSync;
        return this;
    }
}
