package com.pasistence.knockwork.Remote;

import com.pasistence.knockwork.Model.SearchPageFreelancerModel;
import com.pasistence.knockwork.Model.SearchPageListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {
    @GET("knockwork/public/index.php/api/jobdescriptions/lancer/page/{pageNo}")
    Call<SearchPageListModel> getIP(@Query("pageNo") String pageNo);
}

