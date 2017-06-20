package models.tl;

public class Pais {
    private int id;
    private String nombre;
    private Continente continente;

    public Pais() {
    }

    public Pais(int id, String nombre, Continente continente) {
        this.id = id;
        this.nombre = nombre;
        this.continente = continente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Continente getContinente() {
        return continente;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }
}