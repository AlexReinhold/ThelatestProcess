package models.tl;

import java.sql.Timestamp;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private String salt;
    private int verificado;
    private CodigoUsuario codigoVerificacion;
    private CodigoUsuario codigoCambioClave;
    private Timestamp fechaRegistro;
    private int activo;
    private Rol rol;
    private String nombreUsuario;
    private String facebookId;
    private String googleId;
    private String loginWith;
    private boolean perfilPersonalizado;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public Usuario addId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Usuario addNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public Usuario addApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public Usuario addCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public String getClave() {
        return clave;
    }

    public Usuario addClave(String clave) {
        this.clave = clave;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public Usuario addSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public int getVerificado() {
        return verificado;
    }

    public Usuario addVerificado(int verificado) {
        this.verificado = verificado;
        return this;
    }

    public boolean isPerfilPersonalizado() {
        return perfilPersonalizado;
    }

    public Usuario addPerfilPersonalizado(boolean perfilPersonalizado) {
        this.perfilPersonalizado = perfilPersonalizado;
        return this;
    }

    public CodigoUsuario getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public Usuario addCodigoVerificacion(CodigoUsuario codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
        return this;
    }

    public CodigoUsuario getCodigoCambioClave() {
        return codigoCambioClave;
    }

    public Usuario addCodigoCambioClave(CodigoUsuario codigoCambioClave) {
        this.codigoCambioClave = codigoCambioClave;
        return this;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public Usuario addFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public int getActivo() {
        return activo;
    }

    public Usuario addActivo(int activo) {
        this.activo = activo;
        return this;
    }

    public Rol getRol() {
        return rol;
    }

    public Usuario addRol(Rol rol) {
        this.rol = rol;
        return this;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Usuario addNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public Usuario addFacebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;
    }

    public String getGoogleId() {
        return googleId;
    }

    public Usuario addGoogleId(String googleId) {
        this.googleId = googleId;
        return this;
    }

    public String getLoginWith() {
        return loginWith;
    }

    public Usuario addLoginWith(String loginWith) {
        this.loginWith = loginWith;
        return this;
    }
}