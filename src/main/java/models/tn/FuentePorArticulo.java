package models.tn;

public class FuentePorArticulo {
    private int id;
    private Ciudad ciudad;
    private Fuente fuente;
    private Tipofuente Tipofuente;

    public FuentePorArticulo() {

    }

    public int getId() {
        return id;
    }

    public FuentePorArticulo addId(int id) {
        this.id = id;
        return this;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public FuentePorArticulo addCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public Fuente getFuente() {
        return fuente;
    }

    public FuentePorArticulo addFuente(Fuente fuente) {
        this.fuente = fuente;
        return this;
    }

    public models.tn.Tipofuente getTipofuente() {
        return Tipofuente;
    }

    public FuentePorArticulo addTipoFuente(models.tn.Tipofuente tipofuente) {
        Tipofuente = tipofuente;
        return this;
    }
}