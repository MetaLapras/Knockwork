package com.pasistence.knockwork.Model;

public class JobList {
    public String jd_id;
    public String jd_title;
    public String jd_price_type;
    public String jd_price;
    public String jd_description;
    public String jd_updated_at = " ";
    public String jd_quotes;
    public String jd_client;

    public JobList(String jd_id, String jd_title, String jd_price_type, String jd_price, String jd_description, String jd_updated_at, String jd_quotes, String jd_client) {
        this.jd_id = jd_id;
        this.jd_title = jd_title;
        this.jd_price_type = jd_price_type;
        this.jd_price = jd_price;
        this.jd_description = jd_description;
        this.jd_updated_at = jd_updated_at;
        this.jd_quotes = jd_quotes;
        this.jd_client = jd_client;
    }

    public JobList() {
    }

    @Override
    public String toString() {
        return "JobList{" +
                "jd_id='" + jd_id + '\'' +
                ", jd_title='" + jd_title + '\'' +
                ", jd_price_type='" + jd_price_type + '\'' +
                ", jd_price='" + jd_price + '\'' +
                ", jd_description='" + jd_description + '\'' +
                ", jd_updated_at='" + jd_updated_at + '\'' +
                ", jd_quotes='" + jd_quotes + '\'' +
                ", jd_client='" + jd_client + '\'' +
                '}';
    }

    public String getJd_id() {
        return jd_id;
    }

    public void setJd_id(String jd_id) {
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
}