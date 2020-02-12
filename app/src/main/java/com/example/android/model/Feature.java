package com.example.android.model;

import java.util.Date;

public class Feature {
    private int id;
    private String identification;
    private String object;
    private String geography;
    private String artist;
    private String material;
    private String desc;
    private String substrate;
    private Date date;
    private String imageUrl;

    public Feature(int id, String identification, String object, String geography, String artist, String material, String desc, String substrate, Date date, String imageUrl) {
        this.id = id;
        this.identification = identification;
        this.object = object;
        this.geography = geography;
        this.artist = artist;
        this.material = material;
        this.desc = desc;
        this.substrate = substrate;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getIdentification() {
        return identification;
    }

    public String getObject() {
        return object;
    }

    public String getGeography() {
        return geography;
    }

    public String getArtist() {
        return artist;
    }

    public String getMaterial() {
        return material;
    }

    public String getDesc() {
        return desc;
    }

    public String getSubstrate() {
        return substrate;
    }

    public Date getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
