package com.pasistence.knockwork.Model.ApiResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponseRegisterLancer {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("page_no")
    @Expose
    private String pageNo;
    @SerializedName("TotalCount")
    @Expose
    private String totalCount;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Lancer")
    @Expose
    private ArrayList<Lancer> lancer = null;

    @Override
    public String toString() {
        return "ApiResponseRegisterLancer{" +
                "error=" + error +
                ", pageNo='" + pageNo + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", message='" + message + '\'' +
                ", lancer=" + lancer +
                '}';
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Lancer> getLancer() {
        return lancer;
    }

    public void setLancer(ArrayList<Lancer> lancer) {
        this.lancer = lancer;
    }

    public class Lancer implements Serializable {

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
        @SerializedName("lancer_mobile_no")
        @Expose
        private String lancerMobileNo;
        @SerializedName("lancer_address_id")
        @Expose
        private String lancerAddressId;
        @SerializedName("lancer_professionalTitle")
        @Expose
        private String lancerProfessionalTitle;
        @SerializedName("lancer_availability")
        @Expose
        private String lancerAvailability;
        @SerializedName("lancer_selfIntro")
        @Expose
        private String lancerSelfIntro;
        @SerializedName("lancer_dob")
        @Expose
        private String lancerDob;
        @SerializedName("lancer_gender")
        @Expose
        private String lancerGender;
        @SerializedName("lancer_CreatedAt")
        @Expose
        private String lancerCreatedAt;
        @SerializedName("lancer_UpdatedAt")
        @Expose
        private String lancerUpdatedAt;
        @SerializedName("lancer_image")
        @Expose
        private String lancerImage;
        @SerializedName("lancer_provider")
        @Expose
        private String lancerProvider;
        @SerializedName("lancer_minHourRate")
        @Expose
        private String lancerMinHourRate;
        @SerializedName("lancer_skills")
        @Expose
        private String lancerSkills;

        @Override
        public String toString() {
            return "Lancer{" +
                    "uid='" + uid + '\'' +
                    ", lancerId='" + lancerId + '\'' +
                    ", lancerName='" + lancerName + '\'' +
                    ", lancerEmail='" + lancerEmail + '\'' +
                    ", lancerMobileNo='" + lancerMobileNo + '\'' +
                    ", lancerAddressId='" + lancerAddressId + '\'' +
                    ", lancerProfessionalTitle='" + lancerProfessionalTitle + '\'' +
                    ", lancerAvailability='" + lancerAvailability + '\'' +
                    ", lancerSelfIntro='" + lancerSelfIntro + '\'' +
                    ", lancerDob='" + lancerDob + '\'' +
                    ", lancerGender='" + lancerGender + '\'' +
                    ", lancerCreatedAt='" + lancerCreatedAt + '\'' +
                    ", lancerUpdatedAt='" + lancerUpdatedAt + '\'' +
                    ", lancerImage='" + lancerImage + '\'' +
                    ", lancerProvider='" + lancerProvider + '\'' +
                    ", lancerMinHourRate='" + lancerMinHourRate + '\'' +
                    ", lancerSkills='" + lancerSkills + '\'' +
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

        public String getLancerMobileNo() {
            return lancerMobileNo;
        }

        public void setLancerMobileNo(String lancerMobileNo) {
            this.lancerMobileNo = lancerMobileNo;
        }

        public String getLancerAddressId() {
            return lancerAddressId;
        }

        public void setLancerAddressId(String lancerAddressId) {
            this.lancerAddressId = lancerAddressId;
        }

        public String getLancerProfessionalTitle() {
            return lancerProfessionalTitle;
        }

        public void setLancerProfessionalTitle(String lancerProfessionalTitle) {
            this.lancerProfessionalTitle = lancerProfessionalTitle;
        }

        public String getLancerAvailability() {
            return lancerAvailability;
        }

        public void setLancerAvailability(String lancerAvailability) {
            this.lancerAvailability = lancerAvailability;
        }

        public String getLancerSelfIntro() {
            return lancerSelfIntro;
        }

        public void setLancerSelfIntro(String lancerSelfIntro) {
            this.lancerSelfIntro = lancerSelfIntro;
        }

        public String getLancerDob() {
            return lancerDob;
        }

        public void setLancerDob(String lancerDob) {
            this.lancerDob = lancerDob;
        }

        public String getLancerGender() {
            return lancerGender;
        }

        public void setLancerGender(String lancerGender) {
            this.lancerGender = lancerGender;
        }

        public String getLancerCreatedAt() {
            return lancerCreatedAt;
        }

        public void setLancerCreatedAt(String lancerCreatedAt) {
            this.lancerCreatedAt = lancerCreatedAt;
        }

        public String getLancerUpdatedAt() {
            return lancerUpdatedAt;
        }

        public void setLancerUpdatedAt(String lancerUpdatedAt) {
            this.lancerUpdatedAt = lancerUpdatedAt;
        }

        public String getLancerImage() {
            return lancerImage;
        }

        public void setLancerImage(String lancerImage) {
            this.lancerImage = lancerImage;
        }

        public String getLancerProvider() {
            return lancerProvider;
        }

        public void setLancerProvider(String lancerProvider) {
            this.lancerProvider = lancerProvider;
        }

        public String getLancerMinHourRate() {
            return lancerMinHourRate;
        }

        public void setLancerMinHourRate(String lancerMinHourRate) {
            this.lancerMinHourRate = lancerMinHourRate;
        }

        public String getLancerSkills() {
            return lancerSkills;
        }

        public void setLancerSkills(String lancerSkills) {
            this.lancerSkills = lancerSkills;
        }
    }
}