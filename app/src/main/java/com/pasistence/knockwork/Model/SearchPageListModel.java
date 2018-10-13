package com.pasistence.knockwork.Model;

import java.util.List;

public class SearchPageListModel {
    public String page ;
    public int count ;
    public String total_count ;
    public List<JobList> job_list ;

    public SearchPageListModel(String page, int count, String total_count, List<JobList> job_list) {
        this.page = page;
        this.count = count;
        this.total_count = total_count;
        this.job_list = job_list;
    }

    public SearchPageListModel() {
    }

    @Override
    public String toString() {
        return "SearchPageListModel{" +
                "page='" + page + '\'' +
                ", count=" + count +
                ", total_count='" + total_count + '\'' +
                ", job_list=" + job_list +
                '}';
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<JobList> getJob_list() {
        return job_list;
    }

    public void setJob_list(List<JobList> job_list) {
        this.job_list = job_list;
    }
}

   /* private String page ;
    private String count ;
    private String total_count ;
    private List<JobList> job_list ;
    public boolean error;

    public SearchPageListModel(String page, String count, String total_count, List<JobList> job_list,boolean error) {
        this.page = page;
        this.count = count;
        this.total_count = total_count;
        this.job_list = job_list;
        this.error = error;

    }

    public SearchPageListModel(String page, String count, String toatl_count, String job_list, boolean error) {
    }



    @Override
    public String toString() {
        return "SearchPageListModel{" +
                "page='" + page + '\'' +
                ", count=" + count +
                ", total_count='" + total_count + '\'' +
                ", job_list=" + job_list +
               "error=" + error +
                '}';
    }
    public boolean isError() {
        return error;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<JobList> getJob_list() {
        return job_list;
    }

    public void setJob_list(List<JobList> job_list) {
        this.job_list = job_list;
    }


   *//* public boolean getPage() {
        return page;
    }*//*
}


   *//* String job_list;
    @SerializedName("job_list")
    private ArrayList<SearchPageFreelancerModel> searchList;

    public ArrayList<SearchPageFreelancerModel> getsearchArrayList() {
        return searchList;
    }

    public void setsearchArrayList(ArrayList<SearchPageFreelancerModel> noticeArrayList) {
        this.searchList = noticeArrayList;
    }*//*
*/