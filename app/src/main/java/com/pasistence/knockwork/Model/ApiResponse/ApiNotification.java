package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiNotification {

    @SerializedName("notification")
    @Expose
    private Notification notification;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("priority")
    @Expose
    private String priority;

    public ApiNotification(Notification notification, String to, String priority) {
        this.notification = notification;
        this.to = to;
        this.priority = priority;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public static class Notification {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("body")
        @Expose
        private String body;
        @SerializedName("sound")
        @Expose
        private String sound;
        @SerializedName("click_action")
        @Expose
        private String clickAction;
        @SerializedName("icon")
        @Expose
        private String icon;

        public Notification(String title, String body, String sound, String clickAction, String icon) {
            this.title = title;
            this.body = body;
            this.sound = sound;
            this.clickAction = clickAction;
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public String getClickAction() {
            return clickAction;
        }

        public void setClickAction(String clickAction) {
            this.clickAction = clickAction;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }
}