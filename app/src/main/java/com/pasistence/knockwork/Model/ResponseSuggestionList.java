package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class ResponseSuggestionList implements Serializable {
    public String title ;
    public ResponseSuggestionList() {
    }

    public ResponseSuggestionList(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ResponseSuggestionList{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
