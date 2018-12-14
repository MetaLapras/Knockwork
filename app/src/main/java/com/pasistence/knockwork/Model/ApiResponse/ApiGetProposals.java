package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiGetProposals implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("lid")
    @Expose
    private String lid;
    @SerializedName("jobid")
    @Expose
    private String jobid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("createdat")
    @Expose
    private String createdat;
    @SerializedName("updatedat")
    @Expose
    private String updatedat;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ApiGetProposals{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", lid='" + lid + '\'' +
                ", jobid='" + jobid + '\'' +
                ", title='" + title + '\'' +
                ", rate='" + rate + '\'' +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                ", duration='" + duration + '\'' +
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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