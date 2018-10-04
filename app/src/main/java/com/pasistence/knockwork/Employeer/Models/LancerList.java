package com.pasistence.knockwork.Employeer.Models;

import java.io.Serializable;

public class LancerList implements Serializable {
    String Id,name,description,country,earning,like,image;

    public LancerList(String id, String name, String description, String country, String earning, String like, String image) {
        Id = id;
        this.name = name;
        this.description = description;
        this.country = country;
        this.earning = earning;
        this.like = like;
        this.image = image;
    }

    public LancerList() {
    }

    @Override
    public String toString() {
        return "LancerList{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", earning='" + earning + '\'' +
                ", like='" + like + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
