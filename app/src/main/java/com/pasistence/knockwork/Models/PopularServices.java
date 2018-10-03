package com.pasistence.knockwork.Models;

import java.io.Serializable;

public class PopularServices implements Serializable {
    public String id,name,image;

    public PopularServices(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public PopularServices() {
    }

    @Override
    public String toString() {
        return "PopularServices{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
