package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class ResponseTopService implements Serializable {
    public String categories_id ;
    public String categories_title ;
    public String categories_image_url ;
    public String categories_description ;


    public ResponseTopService(String categories_id, String categories_title, String categories_image_url, String categories_description) {
        this.categories_id = categories_id;
        this.categories_title = categories_title;
        this.categories_image_url = categories_image_url;
        this.categories_description = categories_description;
    }

    public ResponseTopService() {
    }

    @Override
    public String toString() {
        return "ResponseTopService{" +
                "categories_id='" + categories_id + '\'' +
                ", categories_title='" + categories_title + '\'' +
                ", categories_image_url='" + categories_image_url + '\'' +
                ", categories_description='" + categories_description + '\'' +
                '}';
    }

    public String getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(String categories_id) {
        this.categories_id = categories_id;
    }

    public String getCategories_title() {
        return categories_title;
    }

    public void setCategories_title(String categories_title) {
        this.categories_title = categories_title;
    }

    public String getCategories_image_url() {
        return categories_image_url;
    }

    public void setCategories_image_url(String categories_image_url) {
        this.categories_image_url = categories_image_url;
    }

    public String getCategories_description() {
        return categories_description;
    }

    public void setCategories_description(String categories_description) {
        this.categories_description = categories_description;
    }
}
