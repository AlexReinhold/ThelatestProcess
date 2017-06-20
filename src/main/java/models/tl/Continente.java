package models.tl;



public class Continente {

    private int id;
    private String nombre;

    public Continente() {

    }

    public Continente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getnombre() {

        return nombre;
    }

    public void setnombre(String nombre) {

        this.nombre = nombre;
    }
}