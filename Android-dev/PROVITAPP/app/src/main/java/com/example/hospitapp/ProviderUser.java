package com.example.hospitapp;

import java.io.Serializable;

public class ProviderUser implements Serializable {

    private int id;
    private String nombre;
    private String usuario;
    private String password;
    private String direccion;
    private String email;
    private String telefono;
    private String numberAddress;
    private String zipCode;
    private String city;
    private String descripcion;

    public ProviderUser(int id, String nombre, String usuario, String direccion, String email, String telefono, String numberAddress, String zipCode, String city, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.numberAddress = numberAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.descripcion = descripcion;
    }


    /**         GETTER FUNCTIONS       */
    public String getNombre() {
        return nombre;
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

    public String getDireccion() {
        return direccion;
    }

    public String getNumberAddress() {
        return numberAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    /**         SETTER FUNCTIONS       */

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
