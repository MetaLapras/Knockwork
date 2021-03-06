package com.pasistence.knockwork.ChatBox.ChatModel;

import java.io.Serializable;

public class FirebaseUidModel implements Serializable {
    String client_id,lancer_id,lancer_name,lancer_url,client_name,client_url,last_message;
    Long last_date;
    public FirebaseUidModel() {
    }

    public FirebaseUidModel(String client_id, String lancer_id, String lancer_name, String lancer_url, String client_name, String client_url, String last_message) {
        this.client_id = client_id;
        this.lancer_id = lancer_id;
        this.lancer_name = lancer_name;
        this.lancer_url = lancer_url;
        this.client_name = client_name;
        this.client_url = client_url;
        this.last_message = last_message;
    }

    public FirebaseUidModel(String client_id, String lancer_id, String lancer_name, String lancer_url, String client_name, String client_url, String last_message,Long last_date) {
        this.client_id = client_id;
        this.lancer_id = lancer_id;
        this.lancer_name = lancer_name;
        this.lancer_url = lancer_url;
        this.client_name = client_name;
        this.client_url = client_url;
        this.last_message = last_message;
        this.last_date = last_date;
    }

    public Long getLast_date() {
        return last_date;
    }

    public void setLast_date(Long last_date) {
        this.last_date = last_date;
    }

    @Override
    public String toString() {
        return "FirebaseUidModel{" +
                "client_id='" + client_id + '\'' +
                ", lancer_id='" + lancer_id + '\'' +
                ", lancer_name='" + lancer_name + '\'' +
                ", lancer_url='" + lancer_url + '\'' +
                ", client_name='" + client_name + '\'' +
                ", client_url='" + client_url + '\'' +
                ", last_message='" + last_message + '\'' +
                ", last_date=" + last_date +
                '}';
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getLancer_id() {
        return lancer_id;
    }

    public void setLancer_id(String lancer_id) {
        this.lancer_id = lancer_id;
    }

    public String getLancer_name() {
        return lancer_name;
    }

    public void setLancer_name(String lancer_name) {
        this.lancer_name = lancer_name;
    }

    public String getLancer_url() {
        return lancer_url;
    }

    public void setLancer_url(String lancer_url) {
        this.lancer_url = lancer_url;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_url() {
        return client_url;
    }

    public void setClient_url(String client_url) {
        this.client_url = client_url;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }
}
