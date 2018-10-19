package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class PopularServicesModel implements Serializable {
    public String id ;
    public String title ;
    public String image_url ;

    public PopularServicesModel(String id, String title, String image_url) {
        this.id = id;
        this.title = title;
        this.image_url = image_url;
    }

    public PopularServicesModel() {
    }

    @Override
    public String toString() {
        return "PopularServicesModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
