package com.pasistence.knockwork.Employeer.Models;

import java.io.Serializable;

public class TopServices implements Serializable {
    public String id,logo,head,content,image;

    public TopServices(String id, String logo, String head, String content, String image) {
        this.id = id;
        this.logo = logo;
        this.head = head;
        this.content = content;
        this.image = image;
    }

    public TopServices() {
    }

    @Override
    public String toString() {
        return "TopServices{" +
                "id='" + id + '\'' +
                ", logo='" + logo + '\'' +
                ", head='" + head + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
