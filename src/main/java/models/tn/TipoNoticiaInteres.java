package models.tn;

/**
 * Created by amendez on 08/05/17.
 */
public class TipoNoticiaInteres {
    private Integer id;
    private String nombre;


    public Integer getId() {
        return id;
    }

    public TipoNoticiaInteres setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoNoticiaInteres setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

}
