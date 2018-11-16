package com.pasistence.knockwork.ChatBox.ChatModel;

import com.google.firebase.database.ServerValue;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class FirebaseChatMessage implements Serializable {
    public long created;
    public String text;
    public String uid;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public FirebaseChatMessage() {
    }

    @Override
    public String toString() {
        return "FirebaseChatMessage{" +
                ", created=" + created +
                ", text='" + text + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }

    public FirebaseChatMessage(long created, String text, String uid) {
        this.created = created;
        this.text = text;
        this.uid = uid;
    }

    public FirebaseChatMessage(String text, String uid) {
       // this.id = id;
        this.text = text;
        this.uid = uid;

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
