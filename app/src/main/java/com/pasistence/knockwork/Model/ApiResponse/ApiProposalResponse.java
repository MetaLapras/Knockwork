package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiProposalResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiProposalResponse{" +
                "error=" + error +
                ", details=" + details +
                ", message='" + message + '\'' +
                '}';
    }


    public class Details {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("lid")
        @Expose
        private String lid;
        @SerializedName("job_id")
        @Expose
        private String jobId;
        @SerializedName("createdat")
        @Expose
        private String createdat;
        @SerializedName("updatedat")
        @Expose
        private String updatedat;

        @Override
        public String toString() {
            return "Details{" +
                    "id='" + id + '\'' +
                    ", uid='" + uid + '\'' +
                    ", lid='" + lid + '\'' +
                    ", jobId='" + jobId + '\'' +
                    ", createdat='" + createdat + '\'' +
                    ", updatedat='" + updatedat + '\'' +
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

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public String getCreatedat() {
            return createdat;
        }

        public void setCreatedat(String createdat) {
            this.createdat = createdat;
        }

        public String getUpdatedat() {
            return updatedat;
        }

        public void setUpdatedat(String updatedat) {
            this.updatedat = updatedat;
        }

    }
}