package com.example.hospitapp;

public class Proveedor {

    private int id;
    private String nombre;
    private String usuario;
    private String password;
    private String direccion;
    private String telefono;
    private String nameBusiness;
    private String email;

    public Proveedor(int id, String nombre, String usuario, String nameBusiness, String email, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.nameBusiness = nameBusiness;
        this.email = email;
        this.usuario = usuario;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Proveedor(int id, String nombre, String usuario, String password, String nameBusiness, String email, String direccion, String telefono) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.nameBusiness = nameBusiness;
        this.email = email;
        this.usuario = usuario;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /** _______________ GETTER FUNCTIONS _______________ */
    public String getNombre() {
        return nombre;
    }

    public String getNameBusiness() {
        return nameBusiness;
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNameBusiness(String nameBusiness) {
        this.nameBusiness = nameBusiness;
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
