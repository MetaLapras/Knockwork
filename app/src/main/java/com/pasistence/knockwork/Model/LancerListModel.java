package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class LancerListModel implements Serializable {
    public String f_id ;
    public String ur_id ;
    public String first_name ;
    public String last_name;
    public String phone_no;
    public String email_address ;
    public String image_url ;
    public String country;
    public String description ;
    public String earning ;
    public String feedback ;
  //  public String like,category;


    public LancerListModel(String id, String name, String description, String country, String earning, String like, String image, String category) {
        this.f_id = id;
        this.first_name = name;
        this.description = description;
        this.country = country;
        this.earning = earning;
     //   this.like = like;
        this.image_url = image;
      //  this.category = category;
    }

    public LancerListModel() {
    }

    @Override
    public String toString() {
        return "LancerListModel{" +
                "f_id='" + f_id + '\'' +
                ", ur_id='" + ur_id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", email_address='" + email_address + '\'' +
                ", image_url='" + image_url + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", earning='" + earning + '\'' +
                ", feedback='" + feedback + '\'' +
                //", like='" + like + '\'' +
                //", category='" + category + '\'' +
                '}';
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
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

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEarning() {
        return earning;
    }

    public void setEarning(String earning) {
        this.earning = earning;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

   /* public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }*/
}
