package com.pasistence.knockwork.Model;

public  class JdClient {
    public String first_name ;
    public String last_name ;
    public String country ;
    public String total_spend ;
    public int feedback ;

    public JdClient(String first_name, String last_name, String country, String total_spend, int feedback) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
        this.total_spend = total_spend;
        this.feedback = feedback;
    }

    public JdClient() {
    }

    @Override
    public String toString() {
        return "JdClient{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", country='" + country + '\'' +
                ", total_spend='" + total_spend + '\'' +
                ", feedback=" + feedback +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTotal_spend() {
        return total_spend;
    }

    public void setTotal_spend(String total_spend) {
        this.total_spend = total_spend;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }
}
