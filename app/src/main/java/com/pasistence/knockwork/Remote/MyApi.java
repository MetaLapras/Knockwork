package com.pasistence.knockwork.Remote;

import com.pasistence.knockwork.Model.ApiResponse.ApiEducationResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterClient;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseUpdateLancer;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.Model.ResponseSubCategory;
import com.pasistence.knockwork.Model.ResponseSuggestionList;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.Model.SearchPageListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {

    @GET("knockwork/public/index.php/api/jobdescriptions/lancer/page/{pageNo}")
    Call<SearchPageListModel> getIP(@Query("pageNo") String pageNo);

    @GET("categories")
    Call<List<ResponseTopService>> getTopServices();

   @GET("popularservices")
    Call<List<PopularServicesModel>> getPopularServices();

    @GET("subcategories/{id}")
    Call<List<ResponseSubCategory>>getSubCategoryById(@Path("id")String catId);

    @GET("suggestionlist")
    Call<List<ResponseSuggestionList>>getSuggestionList();

    @GET("lancers/{pageNo}")
    Call<ApiResponseLancer>getLancers(@Path("pageNo") int pageNo);

    @FormUrlEncoded
    @POST("lancersearch")
    Call<ApiResponseLancer>LancerSearch(@Field("pageNo") int pageNo, @Field("title") CharSequence Title);

    @FormUrlEncoded
    @POST("lancerRegistration")
    Call<ApiResponseRegisterLancer>RegisterLancer(
            @Field("uid") String Uid,
            @Field("user_name")  String name,
            @Field("user_email")  String email,
            @Field("user_image")  String image,
            @Field("user_provider")  String provide,
            @Field("user_phone_no")  String phoneNo);

    @FormUrlEncoded
    @POST("clientRegistration")
    Call<ApiResponseRegisterClient>RegisterClient(
            @Field("uid") String Uid,
            @Field("user_name")  String name,
            @Field("user_email")  String email,
            @Field("user_image")  String image,
            @Field("user_provider")  String provide,
            @Field("user_phone_no")  String phoneNo);


    @FormUrlEncoded
    @POST("lancerUpdate")
    Call<ApiResponseUpdateLancer>updateLancerProfile(
                    @Field("uid") String Uid,
                    @Field("l_id")  String Lid,
                    @Field("user_title")  String title,
                    @Field("user_avaiable")  String avaiable,
                    @Field("user_selfintro")  String selfintro,
                    @Field("user_dob")  String dob,
                    @Field("user_gender")  String gender,
                    @Field("user_minhrrate")  String minhrrate,
                    @Field("user_skills")  String skills,
                    @Field("user_mobile")  String mobile);

    @FormUrlEncoded
    @POST("lancerEducational")
    Call<ApiEducationResponse>LancerProfileEducation(
            @Field("uid") String Uid,
            @Field("l_id")  String Lid,
            @Field("user_degree")  String degree,
            @Field("user_percentage")  String percentage,
            @Field("user_passingyear")  String passingyear,
            @Field("user_university")  String university);

    @FormUrlEncoded
    @POST("lancerExperience")
    Call<ApiResponseUpdateLancer>LancerProfileExperience(
            @Field("uid") String Uid,
            @Field("l_id")  String Lid,
            @Field("company_name")  String companyName,
            @Field("job_description")  String description,
            @Field("start_date")  String startDate,
            @Field("end_date")  String endDate);




}

