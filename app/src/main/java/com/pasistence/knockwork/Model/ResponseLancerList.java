package com.pasistence.knockwork.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseLancerList implements Serializable {
    @SerializedName("Error")
    @Expose
    private Boolean error;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("pageno")
    @Expose
    private String pageno;
    @SerializedName("totalcount")
    @Expose
    private String totalcount;
    @SerializedName("LancerListModel")
    @Expose
    private List<LancerListModel> lancerListModel = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
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

    public List<LancerListModel> getLancerListModel() {
        return lancerListModel;
    }

    public void setLancerListModel(List<LancerListModel> lancerListModel) {
        this.lancerListModel = lancerListModel;
    }
}
