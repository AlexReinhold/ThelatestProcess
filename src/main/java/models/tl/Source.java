package models.tl;

public class Source {
    private int id;
    private String nombre;
    private String url;
    private int idExterno;
    private String slug;
    private String nombreCorto;
    private String favicon;

    public Source() {
    }

    public Source(int id, String nombre, String url, int idExterno, String slug, String nombreCorto, String favicon) {
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

    public Source addId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Source addNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Source addUrl(String url) {
        this.url = url;
        return this;
    }

    public int getIdExterno() {
        return idExterno;
    }

    public Source addIdExterno(int idExterno) {
        this.idExterno = idExterno;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Source addSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public Source addNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
        return this;
    }

    public String getFavicon() {
        return favicon;
    }

    public Source addFavicon(String favicon) {
        this.favicon = favicon;
        return this;
    }
}