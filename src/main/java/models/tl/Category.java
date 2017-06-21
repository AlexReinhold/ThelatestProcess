package models.tl;

public class Category {
    private int id;
    private String nombre;
    private int categoriaPadre;
    private int idExterno;
    private String slug;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public Category addId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Category addNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public int getCategoriaPadre() {
        return categoriaPadre;
    }

    public Category addCategoriaPadre(int categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
        return this;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public Category addIdExterno(int idExterno) {
        this.idExterno = idExterno;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Category addSlug(String slug) {
        this.slug = slug;
        return this;
    }

}