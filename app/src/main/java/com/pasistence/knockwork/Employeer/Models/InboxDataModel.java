package com.pasistence.knockwork.Employeer.Models;

public class InboxDataModel {
    String name;
    String version;
    int id_;
    int image;

    public InboxDataModel(String name, String version, int id_, int image) {
        this.name = name;
        this.version = version;
        this.id_ = id_;
        this.image = image;
    }

   /* public InboxDataModel(String s, String s1) {
    }*/

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public int getId_() {
        return id_;
    }

    public int getImage() {
        return image;
    }
}
