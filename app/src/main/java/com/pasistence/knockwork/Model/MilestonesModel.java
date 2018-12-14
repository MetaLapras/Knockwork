package com.pasistence.knockwork.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MilestonesModel {

    @SerializedName("details")
    @Expose
    private List<Detail> details = null;

    public MilestonesModel(List<Detail> details) {
        this.details = details;
    }


    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public static class Detail {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("pid")
        @Expose
        private String pid;
        @SerializedName("milestone")
        @Expose
        private String milestone;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("duration")
        @Expose
        private String duration;

        @Override
        public String toString() {
            return "Detail{" +
                    "id='" + id + '\'' +
                    ", pid='" + pid + '\'' +
                    ", milestone='" + milestone + '\'' +
                    ", price='" + price + '\'' +
                    ", duration='" + duration + '\'' +
                    '}';
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public Detail(String id, String milestone, String price, String duration) {
            this.id = id;
            this.milestone = milestone;
            this.price = price;
            this.duration = duration;
        }

        public Detail(String id, String pid, String milestone, String price, String duration) {
            this.id = id;
            this.pid = pid;
            this.milestone = milestone;
            this.price = price;
            this.duration = duration;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMilestone() {
            return milestone;
        }

        public void setMilestone(String milestone) {
            this.milestone = milestone;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }
}
