package resource;

public enum FileType {

    VIDEO(1, "VIDEO"),
    IMAGEN(2, "IMAGEN");

    private int id;
    private String tipo;

    FileType(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
}
