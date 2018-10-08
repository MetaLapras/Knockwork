package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class SearchPageFreelancerModel implements Serializable {

    String jobname,fixedprice,pricerange,poasteddays,quotes,description,image,personname,state,projectrange,feedback;

    public SearchPageFreelancerModel(String jobname, String fixedprice, String pricerange, String poasteddays, String quotes, String description, String image, String personname, String state, String projectrange, String feedback) {
        this.jobname = jobname;
        this.fixedprice = fixedprice;
        this.pricerange = pricerange;
        this.poasteddays = poasteddays;
        this.quotes = quotes;
        this.description = description;
        this.image = image;
        this.personname = personname;
        this.state = state;
        this.projectrange = projectrange;
        this.feedback = feedback;
    }

    public SearchPageFreelancerModel(String jobname) {
        this.jobname = jobname;
    }

    @Override
    public String toString() {
        return "SearchPageFreelancerModel{" +
                "jobname='" + jobname + '\'' +
                ", fixedprice='" + fixedprice + '\'' +
                ", pricerange='" + pricerange + '\'' +
                ", poasteddays='" + poasteddays + '\'' +
                ", quotes='" + quotes + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", personname='" + personname + '\'' +
                ", state='" + state + '\'' +
                ", projectrange='" + projectrange + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getFixedprice() {
        return fixedprice;
    }

    public void setFixedprice(String fixedprice) {
        this.fixedprice = fixedprice;
    }

    public String getPricerange() {
        return pricerange;
    }

    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }

    public String getPoasteddays() {
        return poasteddays;
    }

    public void setPoasteddays(String poasteddays) {
        this.poasteddays = poasteddays;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProjectrange() {
        return projectrange;
    }

    public void setProjectrange(String projectrange) {
        this.projectrange = projectrange;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
