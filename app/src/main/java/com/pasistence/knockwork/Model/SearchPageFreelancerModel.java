package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class SearchPageFreelancerModel implements Serializable {
    public int jd_id;
    public String jd_title,jd_price_type,jd_price,jd_description,jd_updated_at,jd_quotes,jd_client;

    public SearchPageFreelancerModel(int jd_id, String jd_title, String jd_price_type, String jd_price, String jd_description, String jd_updated_at, String jd_quotes, String jd_client) {
        this.jd_id = jd_id;
        this.jd_title = jd_title;
        this.jd_price_type = jd_price_type;
        this.jd_price = jd_price;
        this.jd_description = jd_description;
        this.jd_updated_at = jd_updated_at;
        this.jd_quotes = jd_quotes;
        this.jd_client = jd_client;
    }

    public SearchPageFreelancerModel() {
    }

    @Override
    public String toString() {
        return "SearchPageFreelancerModel{" +
                "jd_id=" + jd_id +
                ", jd_title='" + jd_title + '\'' +
                ", jd_price_type='" + jd_price_type + '\'' +
                ", jd_price='" + jd_price + '\'' +
                ", jd_description='" + jd_description + '\'' +
                ", jd_updated_at='" + jd_updated_at + '\'' +
                ", jd_quotes='" + jd_quotes + '\'' +
                ", jd_client='" + jd_client + '\'' +
                '}';
    }

    public int getJd_id() {
        return jd_id;
    }

    public void setJd_id(int jd_id) {
        this.jd_id = jd_id;
    }

    public String getJd_title() {
        return jd_title;
    }

    public void setJd_title(String jd_title) {
        this.jd_title = jd_title;
    }

    public String getJd_price_type() {
        return jd_price_type;
    }

    public void setJd_price_type(String jd_price_type) {
        this.jd_price_type = jd_price_type;
    }

    public String getJd_price() {
        return jd_price;
    }

    public void setJd_price(String jd_price) {
        this.jd_price = jd_price;
    }

    public String getJd_description() {
        return jd_description;
    }

    public void setJd_description(String jd_description) {
        this.jd_description = jd_description;
    }

    public String getJd_updated_at() {
        return jd_updated_at;
    }

    public void setJd_updated_at(String jd_updated_at) {
        this.jd_updated_at = jd_updated_at;
    }

    public String getJd_quotes() {
        return jd_quotes;
    }

    public void setJd_quotes(String jd_quotes) {
        this.jd_quotes = jd_quotes;
    }

    public String getJd_client() {
        return jd_client;
    }

    public void setJd_client(String jd_client) {
        this.jd_client = jd_client;
    }
    /* String jobname,fixedprice,pricerange,poasteddays,quotes,description,image,personname,state,projectrange,feedback;

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
    }*/
}
