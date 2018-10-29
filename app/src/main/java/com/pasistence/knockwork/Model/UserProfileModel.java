package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class UserProfileModel implements Serializable {
    String serverId,Userid,displayname,professionaltitle,minhourrate,availability,selftintro,skills,mobile,dob,gender,degree,passingyear,companyname,profile,startdate,enddate;
    String EducationId,WorkId;
    public UserProfileModel() {
    }

    @Override
    public String toString() {
        return "UserProfileModel{" +
                "serverId='" + serverId + '\'' +
                ", Userid='" + Userid + '\'' +
                ", displayname='" + displayname + '\'' +
                ", professionaltitle='" + professionaltitle + '\'' +
                ", minhourrate='" + minhourrate + '\'' +
                ", availability='" + availability + '\'' +
                ", selftintro='" + selftintro + '\'' +
                ", skills='" + skills + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", degree='" + degree + '\'' +
                ", passingyear='" + passingyear + '\'' +
                ", companyname='" + companyname + '\'' +
                ", profile='" + profile + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", EducationId='" + EducationId + '\'' +
                ", WorkId='" + WorkId + '\'' +
                '}';
    }

    public String getEducationId() {
        return EducationId;
    }

    public void setEducationId(String educationId) {
        EducationId = educationId;
    }

    public String getWorkId() {
        return WorkId;
    }

    public void setWorkId(String workId) {
        WorkId = workId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getProfessionaltitle() {
        return professionaltitle;
    }

    public void setProfessionaltitle(String professionaltitle) {
        this.professionaltitle = professionaltitle;
    }

    public String getMinhourrate() {
        return minhourrate;
    }

    public void setMinhourrate(String minhourrate) {
        this.minhourrate = minhourrate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSelftintro() {
        return selftintro;
    }

    public void setSelftintro(String selftintro) {
        this.selftintro = selftintro;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPassingyear() {
        return passingyear;
    }

    public void setPassingyear(String passingyear) {
        this.passingyear = passingyear;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
