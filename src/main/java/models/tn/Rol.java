package models.tn;

public class Rol {
    int id;
    private String nombre;

    public Rol() {
    }

    public int getId() {
        return id;
    }

    public Rol addId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol addNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
}