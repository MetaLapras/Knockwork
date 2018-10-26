package com.pasistence.knockwork.Model.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponseRegisterLancer {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("lancer")
    @Expose
    private ArrayList<Lancer> lancer = null;

    public ApiResponseRegisterLancer() {
    }

    @Override
    public String toString() {
        return "ApiResponseRegisterLancer{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", lancer=" + lancer +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Lancer> getLancer() {
        return lancer;
    }

    public void setLancer(ArrayList<Lancer> lancer) {
        this.lancer = lancer;
    }


    public class Lancer {

        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("lancer_id")
        @Expose
        private String lancerId;
        @SerializedName("lancer_name")
        @Expose
        private String lancerName;
        @SerializedName("lancer_email")
        @Expose
        private String lancerEmail;
        @SerializedName("lancer_phone_no")
        @Expose
        private String lancerPhoneNo;
        @SerializedName("lancer_provider")
        @Expose
        private String lancerProvider;
        @SerializedName("lancer_image")
        @Expose
        private String lancerImage;

        @Override
        public String toString() {
            return "Lancer{" +
                    "uid='" + uid + '\'' +
                    ", lancerId='" + lancerId + '\'' +
                    ", lancerName='" + lancerName + '\'' +
                    ", lancerEmail='" + lancerEmail + '\'' +
                    ", lancerPhoneNo='" + lancerPhoneNo + '\'' +
                    ", lancerProvider='" + lancerProvider + '\'' +
                    ", lancerImage='" + lancerImage + '\'' +
                    '}';
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getLancerId() {
            return lancerId;
        }

        public void setLancerId(String lancerId) {
            this.lancerId = lancerId;
        }

        public String getLancerName() {
            return lancerName;
        }

        public void setLancerName(String lancerName) {
            this.lancerName = lancerName;
        }

        public String getLancerEmail() {
            return lancerEmail;
        }

        public void setLancerEmail(String lancerEmail) {
            this.lancerEmail = lancerEmail;
        }

        public String getLancerPhoneNo() {
            return lancerPhoneNo;
        }

        public void setLancerPhoneNo(String lancerPhoneNo) {
            this.lancerPhoneNo = lancerPhoneNo;
        }

        public String getLancerProvider() {
            return lancerProvider;
        }

        public void setLancerProvider(String lancerProvider) {
            this.lancerProvider = lancerProvider;
        }

        public String getLancerImage() {
            return lancerImage;
        }

        public void setLancerImage(String lancerImage) {
            this.lancerImage = lancerImage;
        }


    }
}