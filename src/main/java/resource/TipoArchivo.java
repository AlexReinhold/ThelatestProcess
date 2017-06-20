package resource;

/**
 * Created by nlperez on 5/18/17.
 */
public enum TipoArchivo {

    VIDEO(1, "VIDEO"),
    IMAGEN(2, "IMAGEN");

    private int id;
    private String tipo;

    TipoArchivo(int id, String tipo) {
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
