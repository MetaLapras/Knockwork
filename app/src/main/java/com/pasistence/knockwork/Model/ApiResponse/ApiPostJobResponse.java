package com.pasistence.knockwork.Model.ApiResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiPostJobResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("pageno")
    @Expose
    private String pageno;
    @SerializedName("totalcount")
    @Expose
    private String totalcount;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("Result")
    @Expose
    private ArrayList<Result> result = null;

    @Override
    public String toString() {
        return "ApiPostJobResponse{" +
                "error=" + error +
                ", pageno='" + pageno + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getPageno() {
        return pageno;
    }

    public void setPageno(String pageno) {
        this.pageno = pageno;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Result implements Serializable {

        @SerializedName("PId")
        @Expose
        private String pId;
        @SerializedName("Uid")
        @Expose
        private String uid;
        @SerializedName("Cid")
        @Expose
        private String cid;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("subcategory")
        @Expose
        private String subcategory;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("skills")
        @Expose
        private String skills;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("rate")
        @Expose
        private String rate;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("visibility")
        @Expose
        private String visibility;
        @SerializedName("featured")
        @Expose
        private String featured;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;

        @Override
        public String toString() {
            return "Result{" +
                    "pId='" + pId + '\'' +
                    ", uid='" + uid + '\'' +
                    ", cid='" + cid + '\'' +
                    ", category='" + category + '\'' +
                    ", subcategory='" + subcategory + '\'' +
                    ", title='" + title + '\'' +
                    ", details='" + details + '\'' +
                    ", skills='" + skills + '\'' +
                    ", type='" + type + '\'' +
                    ", rate='" + rate + '\'' +
                    ", duration='" + duration + '\'' +
                    ", visibility='" + visibility + '\'' +
                    ", featured='" + featured + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    '}';
        }

        public String getPId() {
            return pId;
        }

        public void setPId(String pId) {
            this.pId = pId;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getVisibility() {
            return visibility;
        }

        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }

        public String getFeatured() {
            return featured;
        }

        public void setFeatured(String featured) {
            this.featured = featured;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}