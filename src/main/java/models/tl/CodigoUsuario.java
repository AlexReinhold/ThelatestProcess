package models.tl;

import java.sql.Timestamp;

/**
 * Created by nlperez on 5/8/17.
 */
public class CodigoUsuario {

    private int id;
    private String codigo;
    private Timestamp fechaVencimiento;
    private int tipoCodigo;

    public int getId() {
        return id;
    }

    public CodigoUsuario addId(int id) {
        this.id = id;
        return this;
    }

    public String getCodigo() {
        return codigo;
    }

    public CodigoUsuario addCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    public CodigoUsuario addFechaVencimiento(Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
        return this;
    }

    public int getTipoCodigo() {
        return tipoCodigo;
    }

    public CodigoUsuario addTipoCodigo(int tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
        return this;
    }
}
