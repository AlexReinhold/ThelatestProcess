package models.tl;

/**
 * Created by amendez on 10/05/17.
 */
public class TipoReaccion {
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public TipoReaccion setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoReaccion setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
}
