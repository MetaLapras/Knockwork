package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class ChattingModel implements Serializable {
    String id,image,name,message,view;

    public ChattingModel(String id, String image, String name, String message, String view) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.message = message;
        this.view = view;
    }

    public ChattingModel() {
    }

    @Override
    public String toString() {
        return "ChattingModel{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", view='" + view + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
