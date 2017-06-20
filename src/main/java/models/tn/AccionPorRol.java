package models.tn;

public class AccionPorRol {
    private Accion accion;
    private Rol rol;

    public AccionPorRol() {
    }

    public AccionPorRol(Accion accion, Rol rol) {
        this.accion = accion;
        this.rol = rol;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}