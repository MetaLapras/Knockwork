package com.pasistence.knockwork.Model.ApiResponse;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiPostContestResponse {

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
        return "ApiPostContestResponse{" +
                "error=" + error +
                ", pageno='" + pageno + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
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

    public class Result {

        @SerializedName("Contestid")
        @Expose
        private String contestid;
        @SerializedName("cid")
        @Expose
        private String cid;
        @SerializedName("uid")
        @Expose
        private String uid;
        @SerializedName("contestTitle")
        @Expose
        private String contestTitle;
        @SerializedName("contestDescrtion")
        @Expose
        private String contestDescrtion;
        @SerializedName("contestDuration")
        @Expose
        private String contestDuration;
        @SerializedName("contestPrizeMoney")
        @Expose
        private String contestPrizeMoney;
        @SerializedName("contestMode")
        @Expose
        private String contestMode;
        @SerializedName("contestType")
        @Expose
        private String contestType;
        @SerializedName("CreatedAt")
        @Expose
        private String createdAt;
        @SerializedName("UpdatedAt")
        @Expose
        private String updatedAt;

        @Override
        public String toString() {
            return "Result{" +
                    "contestid='" + contestid + '\'' +
                    ", cid='" + cid + '\'' +
                    ", uid='" + uid + '\'' +
                    ", contestTitle='" + contestTitle + '\'' +
                    ", contestDescrtion='" + contestDescrtion + '\'' +
                    ", contestDuration='" + contestDuration + '\'' +
                    ", contestPrizeMoney='" + contestPrizeMoney + '\'' +
                    ", contestMode='" + contestMode + '\'' +
                    ", contestType='" + contestType + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    '}';
        }

        public String getContestid() {
            return contestid;
        }

        public void setContestid(String contestid) {
            this.contestid = contestid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContestTitle() {
            return contestTitle;
        }

        public void setContestTitle(String contestTitle) {
            this.contestTitle = contestTitle;
        }

        public String getContestDescrtion() {
            return contestDescrtion;
        }

        public void setContestDescrtion(String contestDescrtion) {
            this.contestDescrtion = contestDescrtion;
        }

        public String getContestDuration() {
            return contestDuration;
        }

        public void setContestDuration(String contestDuration) {
            this.contestDuration = contestDuration;
        }

        public String getContestPrizeMoney() {
            return contestPrizeMoney;
        }

        public void setContestPrizeMoney(String contestPrizeMoney) {
            this.contestPrizeMoney = contestPrizeMoney;
        }

        public String getContestMode() {
            return contestMode;
        }

        public void setContestMode(String contestMode) {
            this.contestMode = contestMode;
        }

        public String getContestType() {
            return contestType;
        }

        public void setContestType(String contestType) {
            this.contestType = contestType;
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