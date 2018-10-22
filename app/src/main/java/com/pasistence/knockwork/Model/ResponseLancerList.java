package com.pasistence.knockwork.Model;

import java.io.Serializable;
import java.util.List;

public class ResponseLancerList implements Serializable {
    public boolean error;
    public String pageno ;
    public String totalcount ;
    public List<LancerListModel> result ;

    public ResponseLancerList(Boolean error, String pageno, String totalcount, List<LancerListModel> result) {
        this.error = error;
        this.pageno = pageno;
        this.totalcount = totalcount;
        this.result = result;
    }

    public ResponseLancerList() {
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

    public List<LancerListModel> getResult() {
        return result;
    }

    public void setResult(List<LancerListModel> result) {
        this.result = result;
    }
}
