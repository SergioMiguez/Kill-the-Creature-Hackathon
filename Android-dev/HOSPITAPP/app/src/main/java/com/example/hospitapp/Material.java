package com.example.hospitapp;

/**
 * Public class that describes the materials present in the database and that can be ordered by a user.
 */
public class Material {
    /**
     * Field used to store the reference of a given material in the database.
     */
    private int id;
    /**
     * Field used to store the name of a given material present in the database.
     */
    private String materialName;

    /**
     * Constructor of the class used to create an object of the class Material.
     * @param id reference of the material in the database.
     * @param materialName name of the material.
     */
    public Material (int id, String materialName) {
        this.id = id;
        this.materialName = materialName;
    }

    /**         GETTER FUNCTIONS       */
    /**
     * Public function used to make available the id of the material.
     * @return the id of the material (int).
     */
    public int getMaterialId() {
        return id;
    }
    /**
     * Public function used to make available the name of the material.
     * @return the name of the material (String).
     */
    public String getMaterialName() {
        return materialName;
    }

}
