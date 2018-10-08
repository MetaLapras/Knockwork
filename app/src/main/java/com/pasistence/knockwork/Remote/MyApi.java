package com.pasistence.knockwork.Remote;

import com.pasistence.knockwork.Model.SearchPageFreelancerModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    @GET("/")
    Call<SearchPageFreelancerModel> getIP();


}

