package com.example.android.model;

import java.util.Date;

public class Feature {
    private int id;
    private String identificatie;
    private String object;
    private String location;
    private String creator;
    private String material;
    private String description;
    private String undergrond;
    private Date date;
    private String imageUrl;

    public Feature(int id, String identificatie, String object, String location, String creator, String material, String description, String undergrond, Date date, String imageUrl) {
        this.id = id;
        this.identificatie = identificatie;
        this.object = object;
        this.location = location;
        this.creator = creator;
        this.material = material;
        this.description = description;
        this.undergrond = undergrond;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getIdentificatie() {
        return identificatie;
    }

    public String getObject() {
        return object;
    }

    public String getLocation() {
        return location;
    }

    public String getCreator() {
        return creator;
    }

    public String getMaterial() {
        return material;
    }

    public String getDescription() {
        return description;
    }

    public String getUndergrond() {
        return undergrond;
    }

    public Date getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
