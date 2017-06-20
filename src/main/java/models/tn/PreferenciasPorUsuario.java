package models.tn;

/**
 * Created by nlperez on 5/16/17.
 */
public class PreferenciasPorUsuario {

    Usuario usuario;
    Categoria categoria;
    double prioridad;
    boolean off;

    public Usuario getUsuario() {
        return usuario;
    }

    public PreferenciasPorUsuario addUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public PreferenciasPorUsuario addCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public double getPrioridad() {
        return prioridad;
    }

    public PreferenciasPorUsuario addPrioridad(double prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    public boolean isOff() {
        return off;
    }

    public PreferenciasPorUsuario addOff(boolean off) {
        this.off = off;
        return this;
    }
}
