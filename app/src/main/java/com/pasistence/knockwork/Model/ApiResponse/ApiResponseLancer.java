package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseLancer {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("page_no")
    @Expose
    private String pageNo;
    @SerializedName("TotalCount")
    @Expose
    private String totalCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    @Override
    public String toString() {
        return "ApiResponseLancer{" +
                "error=" + error +
                ", pageNo='" + pageNo + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("f_id")
        @Expose
        private String fId;
        @SerializedName("ur_id")
        @Expose
        private String urId;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;
        @SerializedName("email_address")
        @Expose
        private String emailAddress;
        @SerializedName("image_url")
        @Expose
        private String imageUrl;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("earning")
        @Expose
        private String earning;
        @SerializedName("feedback")
        @Expose
        private String feedback;


        @Override
        public String toString() {
            return "Result{" +
                    "fId='" + fId + '\'' +
                    ", urId='" + urId + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", emailAddress='" + emailAddress + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", country='" + country + '\'' +
                    ", description='" + description + '\'' +
                    ", earning='" + earning + '\'' +
                    ", feedback='" + feedback + '\'' +
                    '}';
        }

        public String getFId() {
            return fId;
        }

        public void setFId(String fId) {
            this.fId = fId;
        }

        public String getUrId() {
            return urId;
        }

        public void setUrId(String urId) {
            this.urId = urId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEarning() {
            return earning;
        }

        public void setEarning(String earning) {
            this.earning = earning;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }


    }
}
