package com.example.vinhntph08047_lab4.model;

public class GalleriesModel {
    private String galleries_id;
    private String name;

    public GalleriesModel(String galleries_id, String name) {
        this.galleries_id = galleries_id;
        this.name = name;
    }

    public String getGalleries_id() {
        return galleries_id;
    }

    public void setGalleries_id(String galleries_id) {
        this.galleries_id = galleries_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
