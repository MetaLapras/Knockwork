package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiBidsResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

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

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("lid")
    @Expose
    private String lid;
    @SerializedName("bids")
    @Expose
    private String bids;
    @SerializedName("totalspend")
    @Expose
    private String totalspend;
    @SerializedName("createdat")
    @Expose
    private String createdat;
    @SerializedName("updatedat")
    @Expose
    private String updatedat;

    @Override
    public String toString() {
        return "ApiBidsResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", lid='" + lid + '\'' +
                ", bids='" + bids + '\'' +
                ", totalspend='" + totalspend + '\'' +
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

    public String getBids() {
        return bids;
    }

    public void setBids(String bids) {
        this.bids = bids;
    }

    public String getTotalspend() {
        return totalspend;
    }

    public void setTotalspend(String totalspend) {
        this.totalspend = totalspend;
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