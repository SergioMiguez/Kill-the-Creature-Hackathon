package com.example.hospitapp;

public class Proveedor {

    private String name;
    private String nameBusiness;
    private String email;

    public Proveedor(String name, String nameBusiness, String email, String state) {
        this.name = name;
        this.nameBusiness = nameBusiness;
        this.email = email;
    }

    /** _______________ GETTER FUNCTIONS _______________ */
    public String getName() {
        return name;
    }

    public String getNameBusiness() {
        return nameBusiness;
    }

    public String getEmail() {
        return email;
    }

    /** _______________ SETTER FUNCTIONS _______________ */
    public void setName(String name) {
        this.name = name;
    }

    public void setNameBusiness(String nameBusiness) {
        this.nameBusiness = nameBusiness;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
