package com.example.hospitapp;

import java.io.Serializable;

public class UsuarioHospital implements Serializable {

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

    public UsuarioHospital(int id, String nombre, String usuario, String direccion, String email, String telefono, String numberAddress, String zipCode, String city) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.numberAddress = numberAddress;
        this.zipCode = zipCode;
        this.city = city;
    }

    public UsuarioHospital(int id, String nombre, String usuario, String direccion, String email, String telefono, String numberAddress, String zipCode, String city, String descripcion) {
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

    /*
    public UsuarioHospital (int id, String nombre, String usuario, String password, String email,
                            String direccion, String numberAddress, String zipCode,
                            String city, String telefono) {

        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.direccion = direccion;
        this.numberAddress = numberAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.telefono = telefono;
    }
    */

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

    public String getPassword() {
        return password;
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNumberAddress(String numberAddress) {
        this.numberAddress = numberAddress;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
