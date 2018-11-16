package com.pasistence.knockwork.ChatBox.ChatModel;

import java.io.Serializable;
import java.util.Map;

public class FirebaseChatMessagePost implements Serializable {
    public Map created;
    public String text;
    public String uid;

    public FirebaseChatMessagePost(Map created, String text, String uid) {
        this.created = created;
        this.text = text;
        this.uid = uid;
    }

    public Map getCreated() {
        return created;
    }

    public void setCreated(Map created) {
        this.created = created;
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
