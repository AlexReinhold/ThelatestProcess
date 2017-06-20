package resource;

public enum Process {

    FUENTES(1, "fuentes"),
    CATEGORIAS(2, "categorias"),
    NOTICIAS(3, "noticias");

    private int id;
    private String nombre;

    Process(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
