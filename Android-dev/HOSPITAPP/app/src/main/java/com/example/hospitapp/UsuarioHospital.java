package com.example.hospitapp;

import java.io.Serializable;

public class UsuarioHospital implements Serializable {

    private String name;
    private String password;
    private String email;
    private String hospitalName;
    private String address;
    private String numberAddress;
    private String zipCode;
    private String city;
    private String telephone;

    public UsuarioHospital (String name, String password, String email, String hospitalName,
                            String address, String numberAddress, String zipCode, String city,
                            String telephone) {

        this.name = name;
        this.password = password;
        this.email = email;
        this.hospitalName = hospitalName;
        this.address = address;
        this.numberAddress = numberAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.telephone = telephone;
    }

    /**         GETTER FUNCTIONS       */
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getAddress() {
        return address;
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

    public String getTelephone() {
        return telephone;
    }

    /**         SETTER FUNCTIONS       */
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
