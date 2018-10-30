package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiEducationResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("lancerEducation")
    @Expose
    private ArrayList<LancerEducation> lancerEducation = null;

    @Override
    public String toString() {
        return "ApiEducationResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", lancerEducation=" + lancerEducation +
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

    public ArrayList<LancerEducation> getLancerEducation() {
        return lancerEducation;
    }

    public void setLancerEducation(ArrayList<LancerEducation> lancerEducation) {
        this.lancerEducation = lancerEducation;
    }

    public class LancerEducation {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_degree")
        @Expose
        private String userDegree;
        @SerializedName("user_percentage")
        @Expose
        private String userPercentage;
        @SerializedName("user_passingyear")
        @Expose
        private String userPassingyear;
        @SerializedName("user_university")
        @Expose
        private String userUniversity;
        @SerializedName("user_uid")
        @Expose
        private String userUid;
        @SerializedName("user_l_id")
        @Expose
        private String userLId;

        @Override
        public String toString() {
            return "LancerEducation{" +
                    "id='" + id + '\'' +
                    ", userDegree='" + userDegree + '\'' +
                    ", userPercentage='" + userPercentage + '\'' +
                    ", userPassingyear=" + userPassingyear +
                    ", userUniversity=" + userUniversity +
                    ", userUid='" + userUid + '\'' +
                    ", userLId='" + userLId + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserDegree() {
            return userDegree;
        }

        public void setUserDegree(String userDegree) {
            this.userDegree = userDegree;
        }

        public String getUserPercentage() {
            return userPercentage;
        }

        public void setUserPercentage(String userPercentage) {
            this.userPercentage = userPercentage;
        }

        public String getUserPassingyear() {
            return userPassingyear;
        }

        public void setUserPassingyear(String userPassingyear) {
            this.userPassingyear = userPassingyear;
        }

        public String getUserUniversity() {
            return userUniversity;
        }

        public void setUserUniversity(String userUniversity) {
            this.userUniversity = userUniversity;
        }

        public String getUserUid() {
            return userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
        }

        public String getUserLId() {
            return userLId;
        }

        public void setUserLId(String userLId) {
            this.userLId = userLId;
        }
    }

    }