package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class LancerListModel implements Serializable {
    String Id,name,description,country,earning,like,image,category;

    public LancerListModel(String id, String name, String description, String country, String earning, String like, String image, String category) {
        Id = id;
        this.name = name;
        this.description = description;
        this.country = country;
        this.earning = earning;
        this.like = like;
        this.image = image;
        this.category = category;
    }

    public LancerListModel() {
    }

    @Override
    public String toString() {
        return "LancerListModel{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", earning='" + earning + '\'' +
                ", like='" + like + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}