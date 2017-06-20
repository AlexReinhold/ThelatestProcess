package models.tl;

public class CategoriaPorArticulo {
    private Articulo articulo;
    private Categoria categoria;

    public CategoriaPorArticulo() {

    }

    public CategoriaPorArticulo(Articulo articulo, Categoria categoria) {
        this.articulo = articulo;
        this.categoria = categoria;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
