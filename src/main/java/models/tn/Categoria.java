package models.tn;


public class Categoria {
    private int id;
    private String nombre;
    private int categoriaPadre;
    private int idExterno;
    private String slug;

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public Categoria addId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria addNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public int getCategoriaPadre() {
        return categoriaPadre;
    }

    public Categoria addCategoriaPadre(int categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
        return this;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public Categoria addIdExterno(int idExterno) {
        this.idExterno = idExterno;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Categoria addSlug(String slug) {
        this.slug = slug;
        return this;
    }

}