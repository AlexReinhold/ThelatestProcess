package models.tl;

public class Tipofuente {
    private int id;
    private String tipo;

    public int getId() {
        return id;
    }

    public Tipofuente addId(int id) {
        this.id = id;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Tipofuente addTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }
}