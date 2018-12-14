package com.pasistence.knockwork.Model.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiClientProposalsResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("lid")
    @Expose
    private String lid;
    @SerializedName("jobid")
    @Expose
    private String jobid;
    @SerializedName("createdat")
    @Expose
    private String createdat;
    @SerializedName("updatedat")
    @Expose
    private String updatedat;
    @SerializedName("l_id")
    @Expose
    private String lId;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("l_email")
    @Expose
    private String lEmail;
    @SerializedName("l_mobile_no")
    @Expose
    private String lMobileNo;
    @SerializedName("l_professional_title")
    @Expose
    private String lProfessionalTitle;
    @SerializedName("l_availability")
    @Expose
    private String lAvailability;
    @SerializedName("l_self_intro")
    @Expose
    private String lSelfIntro;
    @SerializedName("l_dob")
    @Expose
    private String lDob;
    @SerializedName("l_gender")
    @Expose
    private String lGender;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("l_profile_image")
    @Expose
    private String lProfileImage;
    @SerializedName("l_provider")
    @Expose
    private String lProvider;
    @SerializedName("l_minhourrate")
    @Expose
    private String lMinhourrate;
    @SerializedName("l_skills")
    @Expose
    private String lSkills;

    @Override
    public String toString() {
        return "ApiClientProposalsResponse{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", lid='" + lid + '\'' +
                ", jobid='" + jobid + '\'' +
                ", createdat='" + createdat + '\'' +
                ", updatedat='" + updatedat + '\'' +
                ", lId='" + lId + '\'' +
                ", uid='" + uid + '\'' +
                ", lName='" + lName + '\'' +
                ", lEmail='" + lEmail + '\'' +
                ", lMobileNo='" + lMobileNo + '\'' +
                ", lProfessionalTitle='" + lProfessionalTitle + '\'' +
                ", lAvailability='" + lAvailability + '\'' +
                ", lSelfIntro='" + lSelfIntro + '\'' +
                ", lDob='" + lDob + '\'' +
                ", lGender='" + lGender + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", lProfileImage='" + lProfileImage + '\'' +
                ", lProvider='" + lProvider + '\'' +
                ", lMinhourrate='" + lMinhourrate + '\'' +
                ", lSkills='" + lSkills + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public String getLId() {
        return lId;
    }

    public void setLId(String lId) {
        this.lId = lId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getLEmail() {
        return lEmail;
    }

    public void setLEmail(String lEmail) {
        this.lEmail = lEmail;
    }

    public String getLMobileNo() {
        return lMobileNo;
    }

    public void setLMobileNo(String lMobileNo) {
        this.lMobileNo = lMobileNo;
    }

    public String getLProfessionalTitle() {
        return lProfessionalTitle;
    }

    public void setLProfessionalTitle(String lProfessionalTitle) {
        this.lProfessionalTitle = lProfessionalTitle;
    }

    public String getLAvailability() {
        return lAvailability;
    }

    public void setLAvailability(String lAvailability) {
        this.lAvailability = lAvailability;
    }

    public String getLSelfIntro() {
        return lSelfIntro;
    }

    public void setLSelfIntro(String lSelfIntro) {
        this.lSelfIntro = lSelfIntro;
    }

    public String getLDob() {
        return lDob;
    }

    public void setLDob(String lDob) {
        this.lDob = lDob;
    }

    public String getLGender() {
        return lGender;
    }

    public void setLGender(String lGender) {
        this.lGender = lGender;
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

    public String getLProfileImage() {
        return lProfileImage;
    }

    public void setLProfileImage(String lProfileImage) {
        this.lProfileImage = lProfileImage;
    }

    public String getLProvider() {
        return lProvider;
    }

    public void setLProvider(String lProvider) {
        this.lProvider = lProvider;
    }

    public String getLMinhourrate() {
        return lMinhourrate;
    }

    public void setLMinhourrate(String lMinhourrate) {
        this.lMinhourrate = lMinhourrate;
    }

    public String getLSkills() {
        return lSkills;
    }

    public void setLSkills(String lSkills) {
        this.lSkills = lSkills;
    }

}