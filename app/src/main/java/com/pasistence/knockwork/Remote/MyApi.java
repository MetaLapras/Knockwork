package com.pasistence.knockwork.Remote;

import com.pasistence.knockwork.Model.SearchPageFreelancerModel;
import com.pasistence.knockwork.Model.SearchPageListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    @GET("/")
    Call<List<SearchPageListModel>> getIP();


}

