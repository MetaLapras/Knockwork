package com.pasistence.knockwork.Model.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiExperienceResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("lancerExperience")
    @Expose
    private ArrayList<LancerExperience> lancerExperience = null;

    @Override
    public String toString() {
        return "ApiExperienceResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", lancerExperience=" + lancerExperience +
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

    public ArrayList<LancerExperience> getLancerExperience() {
        return lancerExperience;
    }

    public void setLancerExperience(ArrayList<LancerExperience> lancerExperience) {
        this.lancerExperience = lancerExperience;
    }

    public class LancerExperience {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("l_id")
        @Expose
        private String lId;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("job_description")
        @Expose
        private String jobDescription;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("end_date")
        @Expose
        private String endDate;

        @Override
        public String toString() {
            return "LancerExperience{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", lId='" + lId + '\'' +
                    ", companyName='" + companyName + '\'' +
                    ", jobDescription='" + jobDescription + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getLId() {
            return lId;
        }

        public void setLId(String lId) {
            this.lId = lId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getJobDescription() {
            return jobDescription;
        }

        public void setJobDescription(String jobDescription) {
            this.jobDescription = jobDescription;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

    }
}