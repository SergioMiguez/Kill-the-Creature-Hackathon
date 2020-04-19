package com.example.hospitapp;

/** Class that is responsible for the creation of an order object*/
public class Order {

    /** Private int that represents the id*/
    private int id;
    /** Private int that represents the id of the object*/
    private int id_object;
    /** Private int that represents the quatity*/
    private int quantity;
    /** Private int that represents the id of the provider*/
    private int id_provider;
    /** Private int that represents the id of the hospital*/
    private int id_hospital;
    /** Private string that represents the date*/
    private String date;
    /** Private string that represents the shipping address*/
    private String shipping_address;
    /** Private string that represents the object name*/
    private String object_name;
    /** Private boolean that represents whether the order was sent or not*/
    private boolean sent;
    /** Private boolean that represents whether the order was received or not*/
    private boolean received;
    /** Private boolean that represents whether the order was completed or not*/
    private boolean completed;

    /** Standard constructor of the order class with no completed parameter*/
    public Order(int id, int id_object, int quantity, int id_provider, int id_hospital, String date, String shipping_address, String object_name, int sent, int received) {
        this.id = id;
        this.id_object = id_object;
        this.quantity = quantity;
        this.id_provider = id_provider;
        this.id_hospital = id_hospital;
        this.date = date;
        this.shipping_address = shipping_address;
        this.object_name = object_name;

        this.sent = sent == 1;
        this.received = received == 1;

    }

    /** Constructor of the order class which overrides the other order constructor if the parameter completed is added*/
    public Order(int id, int id_object, int quantity, int id_provider, int id_hospital, String date, String shipping_address, String object_name, int sent, int received, int completed) {
        this.id = id;
        this.id_object = id_object;
        this.quantity = quantity;
        this.id_provider = id_provider;
        this.id_hospital = id_hospital;
        this.date = date;
        this.shipping_address = shipping_address;
        this.object_name = object_name;

        this.sent = sent == 1;
        this.received = received == 1;
        this.completed = completed == 1;

    }

    /** Constructor of the order class which overrides the other order constructor if no sent, received or completed parameter is used*/
    public Order(int id, int id_object, int quantity, int id_provider, int id_hospital, String date, String shipping_address, String object_name) {
        this.id = id;
        this.id_object = id_object;
        this.quantity = quantity;
        this.id_provider = id_provider;
        this.id_hospital = id_hospital;
        this.date = date;
        this.shipping_address = shipping_address;
        this.object_name = object_name;
    }

    // _______________ GETTER FUNCTIONS _______________

    /** Getter of the id parameter*/
    public int getId() {
        return id;
    }

    /** Getter of the sent parameter*/
    public boolean isSent() {
        return sent;
    }

    /** Getter of the completed parameter*/
    public boolean isCompleted() {
        return completed;
    }

    /** Getter of the received parameter*/
    public boolean isReceived() {
        return received;
    }

    /** Getter of the quantity parameter*/
    public int getQuantity() {
        return quantity;
    }

    /** Getter of the provider id parameter*/
    public int getId_provider() {
        return id_provider;
    }

    /** Getter of the date parameter*/
    public String getDate() {
        return date;
    }

    /** Getter of the object name parameter*/
    public String getObject_name() {return object_name;}

    // _______________ SETTER FUNCTIONS _______________

    /** Setter method of the id parameter*/
    public void setId(int id) {
        this.id = id;
    }

}
