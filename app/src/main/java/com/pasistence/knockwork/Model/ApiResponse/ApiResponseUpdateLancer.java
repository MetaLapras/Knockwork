package com.pasistence.knockwork.Model.ApiResponse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseUpdateLancer implements Serializable{

    private Boolean error;
    private String message;

    public ApiResponseUpdateLancer(Boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public ApiResponseUpdateLancer() {
    }

    @Override
    public String toString() {
        return "ApiResponseUpdateLancer{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
