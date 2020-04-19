package com.example.hospitapp;

public class Order {

    private int id;
    private int id_objeto;
    private int cantidad;
    private int id_proveedor;;
    private int id_hospital;
    private String fecha;
    private String direccion_envio;
    private String nombre_objeto;
    private boolean enviado;
    private boolean recibido;

    public Order(int id, int id_objeto, int cantidad, int id_proveedor, int id_hospital, String fecha, String direccion_envio, String nombre_objeto, int enviado, int recibido) {
        this.id = id;
        this.id_objeto = id_objeto;
        this.cantidad = cantidad;
        this.id_proveedor = id_proveedor;
        this.id_hospital = id_hospital;
        this.fecha = fecha;
        this.direccion_envio = direccion_envio;
        this.nombre_objeto = nombre_objeto;

        this.enviado = enviado == 1;
        this.recibido = recibido == 1;

    }

    public Order(int id, int id_objeto, int cantidad, int id_proveedor, int id_hospital, String fecha, String direccion_envio, String nombre_objeto) {
        this.id = id;
        this.id_objeto = id_objeto;
        this.cantidad = cantidad;
        this.id_proveedor = id_proveedor;
        this.id_hospital = id_hospital;
        this.fecha = fecha;
        this.direccion_envio = direccion_envio;
        this.nombre_objeto = nombre_objeto;
    }

    /** _______________ GETTER FUNCTIONS _______________ */
    public int getId() {
        return id;
    }

    public int getId_objeto() {
        return id_objeto;
    }


    public boolean isEnviado() {
        return enviado;
    }

    public boolean isRecibido() {
        return recibido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public int getId_hospital() {
        return id_hospital;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDireccion_envio() {
        return direccion_envio;
    }

    public String getNombre_objeto() {return nombre_objeto;}

    /** _______________ SETTER FUNCTIONS _______________ */

    public void setId(int id) {
        this.id = id;
    }

    public void setId_objeto(int id_objeto) {
        this.id_objeto = id_objeto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDireccion_envio(String direccion_envio) {
        this.direccion_envio = direccion_envio;
    }

    public void setNombre_objeto(String nombre_objeto) {
        this.nombre_objeto = nombre_objeto;
    }
}
