package com.example.provitapp;

public class Material {

    private int id;
    private String materialName;

    public Material (int id, String materialName) {
        this.id = id;
        this.materialName = materialName;
    }

    /**         GETTER FUNCTIONS       */
    public int getMaterialId() {
        return id;
    }

    public String getMaterialName() {
        return materialName;
    }

    /**         SETTER FUNCTIONS       */
    public void setMaterialId(int id) {
        this.id = id;
    }

    public void setMaterialName (String materialName) {
        this.materialName = materialName;
    }
}
