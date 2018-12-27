package com.pasistence.knockwork.Model.ApiResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ApiHirejobsReponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("subcategory")
    @Expose
    private String subcategory;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("types")
    @Expose
    private String types;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("featured")
    @Expose
    private String featured;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("hireby")
    @Expose
    private String hireby;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getHireby() {
        return hireby;
    }

    public void setHireby(String hireby) {
        this.hireby = hireby;
    }

    @Override
    public String toString() {
        return "ApiHirejobsReponse{" +
                "id='" + id + '\'' +
                ", uId='" + uId + '\'' +
                ", cId='" + cId + '\'' +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", skills='" + skills + '\'' +
                ", types='" + types + '\'' +
                ", rate='" + rate + '\'' +
                ", duration='" + duration + '\'' +
                ", visibility='" + visibility + '\'' +
                ", featured='" + featured + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", hireby='" + hireby + '\'' +
                '}';
    }
}