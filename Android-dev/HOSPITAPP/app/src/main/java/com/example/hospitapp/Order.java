package com.example.hospitapp;

public class Order {

    private String object;
    private String volumeNumber;
    private String state;
    private String referenceID;

    public Order(String object, String volumeNumber, String state, String referenceID) {
        this.object = object;
        this.volumeNumber = volumeNumber;
        this.state = state;
        this.referenceID = referenceID;

    }

    /** _______________ GETTER FUNCTIONS _______________ */

    public String getObject() {
        return object;
    }

    public String getVolumeNumber() {
        return volumeNumber;
    }

    public String getState() {
        return state;
    }

    public String getReferenceID() {
        return referenceID;
    }

    /** _______________ SETTER FUNCTIONS _______________ */

    public void setObject(String object) {
        this.object = object;
    }

    public void setVolumeNumber(String volumeNumber) {
        this.volumeNumber = volumeNumber;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }
}
