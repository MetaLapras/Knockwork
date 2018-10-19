package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class ResponseSubCategory implements Serializable {
    public String sub_categories_id ;
    public String category_id ;
    public String sub_categories_title ;
    public String sub_categories_icon_url ;

    public ResponseSubCategory(String sub_categories_id, String category_id, String sub_categories_title, String sub_categories_icon_url) {
        this.sub_categories_id = sub_categories_id;
        this.category_id = category_id;
        this.sub_categories_title = sub_categories_title;
        this.sub_categories_icon_url = sub_categories_icon_url;
    }

    public ResponseSubCategory() {
    }

    @Override
    public String toString() {
        return "ResponseSubCategory{" +
                "sub_categories_id='" + sub_categories_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", sub_categories_title='" + sub_categories_title + '\'' +
                ", sub_categories_icon_url='" + sub_categories_icon_url + '\'' +
                '}';
    }

    public String getSub_categories_id() {
        return sub_categories_id;
    }

    public void setSub_categories_id(String sub_categories_id) {
        this.sub_categories_id = sub_categories_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSub_categories_title() {
        return sub_categories_title;
    }

    public void setSub_categories_title(String sub_categories_title) {
        this.sub_categories_title = sub_categories_title;
    }

    public String getSub_categories_icon_url() {
        return sub_categories_icon_url;
    }

    public void setSub_categories_icon_url(String sub_categories_icon_url) {
        this.sub_categories_icon_url = sub_categories_icon_url;
    }
}
