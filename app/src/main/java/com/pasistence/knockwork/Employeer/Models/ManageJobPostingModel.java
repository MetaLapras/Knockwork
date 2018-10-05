package com.pasistence.knockwork.Employeer.Models;

import java.io.Serializable;

public class ManageJobPostingModel implements Serializable {
    String Id, jobname, jobfixedprice, jobpricerange, jobpasteddays, jobquots, jobdescription;

    public ManageJobPostingModel(String id, String jobname, String jobfixedprice, String jobpricerange, String jobpasteddays, String jobquots, String jobdescription) {
        Id = id;
        this.jobname = jobname;
        this.jobfixedprice = jobfixedprice;
        this.jobpricerange = jobpricerange;
        this.jobpasteddays = jobpasteddays;
        this.jobquots = jobquots;
        this.jobdescription = jobdescription;
    }

    public ManageJobPostingModel() {
    }

    @Override
    public String toString() {
        return "ManageJobPostingModel{" +
                "Id='" + Id + '\'' +
                ", jobname='" + jobname + '\'' +
                ", jobfixedprice='" + jobfixedprice + '\'' +
                ", jobpricerange='" + jobpricerange + '\'' +
                ", jobpasteddays='" + jobpasteddays + '\'' +
                ", jobquots='" + jobquots + '\'' +
                ", jobdescription='" + jobdescription + '\'' +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobfixedprice() {
        return jobfixedprice;
    }

    public void setJobfixedprice(String jobfixedprice) {
        this.jobfixedprice = jobfixedprice;
    }

    public String getJobpricerange() {
        return jobpricerange;
    }

    public void setJobpricerange(String jobpricerange) {
        this.jobpricerange = jobpricerange;
    }

    public String getJobpasteddays() {
        return jobpasteddays;
    }

    public void setJobpasteddays(String jobpasteddays) {
        this.jobpasteddays = jobpasteddays;
    }

    public String getJobquots() {
        return jobquots;
    }

    public void setJobquots(String jobquots) {
        this.jobquots = jobquots;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }
}

