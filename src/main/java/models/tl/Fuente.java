package models.tl;

public class Fuente {
    private int id;
    private String nombre;
    private String url;
    private int idExterno;
    private String slug;
    private String nombreCorto;
    private String favicon;

    public Fuente() {
    }

    public Fuente(int id, String nombre, String url, int idExterno, String slug, String nombreCorto, String favicon) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.idExterno = idExterno;
        this.slug = slug;
        this.nombreCorto = nombreCorto;
        this.favicon = favicon;
    }

    public int getId() {
        return id;
    }

    public Fuente addId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Fuente addNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Fuente addUrl(String url) {
        this.url = url;
        return this;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public Fuente addIdExterno(int idExterno) {
        this.idExterno = idExterno;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Fuente addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public Fuente addNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
        return this;
    }

    public String getFavicon() {
        return favicon;
    }

    public Fuente addFavicon(String favicon) {
        this.favicon = favicon;
        return this;
    }
}