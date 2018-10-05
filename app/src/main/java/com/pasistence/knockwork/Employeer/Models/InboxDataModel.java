package com.pasistence.knockwork.Employeer.Models;

import java.io.Serializable;

public class InboxDataModel implements Serializable {
    String id,name,description,canDo,image;

    public InboxDataModel(String id, String name, String description, String canDo, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.canDo = canDo;
        this.image = image;
    }

    public InboxDataModel() {
    }

    @Override
    public String toString() {
        return "InboxDataModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", canDo='" + canDo + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCanDo() {
        return canDo;
    }

    public void setCanDo(String canDo) {
        this.canDo = canDo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
