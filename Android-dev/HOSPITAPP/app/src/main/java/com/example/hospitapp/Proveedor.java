package com.example.hospitapp;

public class Proveedor {

    private int id;
    private String usuario;
    private String password;
    private String direccion;
    private String telefono;
    private String email;
    private String descripcion;

    public Proveedor(int id, String usuario, String email, String direccion, String telefono, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.email = email;
        this.usuario = usuario;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Proveedor(int id, String usuario,String password, String descripcion, String email, String direccion, String telefono) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.usuario = usuario;
        this.telefono = telefono;
    }

    /** _______________ GETTER FUNCTIONS _______________ */
    public String getDescripcion() {
        return descripcion;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    /** _______________ SETTER FUNCTIONS _______________ */
    public void setDescripcion(String nameBusiness) {
        this.descripcion = descripcion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
