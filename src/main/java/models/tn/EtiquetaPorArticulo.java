package models.tn;

public class EtiquetaPorArticulo {
    private Etiqueta etiqueta;
    private Articulo articulo;

    public EtiquetaPorArticulo() {

    }

    public EtiquetaPorArticulo(Etiqueta etiqueta, Articulo articulo) {
        this.etiqueta = etiqueta;
        this.articulo = articulo;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}