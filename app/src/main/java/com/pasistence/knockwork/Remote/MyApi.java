package com.pasistence.knockwork.Remote;

import com.pasistence.knockwork.Model.ApiResponse.ApiBidsResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiClientProposalsResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiEducationResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiExperienceResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiGetProposals;
import com.pasistence.knockwork.Model.ApiResponse.ApiHirejobsReponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiNotification;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostContestResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiProfileStatus;
import com.pasistence.knockwork.Model.ApiResponse.ApiProposalResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterClient;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseUpdateLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiSkillsResponse;
import com.pasistence.knockwork.Model.MilestonesModel;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.Model.ResponseSubCategory;
import com.pasistence.knockwork.Model.ResponseSuggestionList;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.Model.SearchPageListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {

    @GET("knockwork/public/index.php/api/jobdescriptions/lancer/page/{pageNo}")
    Call<SearchPageListModel> getIP(@Query("pageNo") String pageNo);

    @GET("categories")
    Call<List<ResponseTopService>> getTopServices();

    @GET("subcategories")
    Call<List<ResponseSubCategory>> getSubCategories();


    @GET("popularservices")
    Call<List<PopularServicesModel>> getPopularServices();

    @GET("subcategories/{id}")
    Call<List<ResponseSubCategory>>getSubCategoryById(@Path("id")String catId);

    @GET("suggestionlist")
    Call<List<ResponseSuggestionList>>getSuggestionList();

    @GET("lancers/{pageNo}")
    Call<ApiResponseRegisterLancer>getLancers(@Path("pageNo") int pageNo);


// USER SIDE  -------------------------------------------------------/
    @FormUrlEncoded
    @POST("lancersearch")
    Call<ApiResponseRegisterLancer>LancerSearch(@Field("pageNo") int pageNo, @Field("title") CharSequence Title);

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
    Call<ApiExperienceResponse>LancerProfileExperience(
            @Field("uid") String Uid,
            @Field("l_id")  String Lid,
            @Field("company_name")  String companyName,
            @Field("job_description")  String description,
            @Field("start_date")  String startDate,
            @Field("end_date")  String endDate);




    //hireJobs
    @FormUrlEncoded
    @POST("hireJobs")
    Call<List<ApiHirejobsReponse>>LancerHireJobs(
            @Field("LancerId") String LancerId
    );



    //Check if lancer exist
    @GET("checkClientExist/{uid}")
    Call<ApiResponseRegisterClient>checkClientexist(@Path("uid") String uid);


    //Check if client exist
    @GET("checkLancerExist/{uid}")
    Call<ApiResponseRegisterLancer>checkLancerexist(@Path("uid") String uid);



    // JOB POSTING-------------------------------------------------------/
    //Post New Job
    @FormUrlEncoded
    @POST("postjob")
    Call<ApiPostJobResponse>ClientPostAJob(
            @Field("uid") String Uid,
            @Field("cid")  String cid,
            @Field("category")  String category,
            @Field("subcategory")  String subcategory,
            @Field("title")  String title,
            @Field("details")  String details,
            @Field("skills")  String skills,
            @Field("types")  String types,
            @Field("rate")  String rate,
            @Field("duration")  String duration,
            @Field("visibility")  String visibility,
            @Field("featured")  String featured
    );

    //Update Posted Job
    @FormUrlEncoded
    @POST("postjobUpdate")
    Call<ApiPostJobResponse>ClientPostAJobUpdate(
            @Field("pid") String Pid,
            @Field("uid") String Uid,
            @Field("cid")  String cid,
            @Field("category")  String category,
            @Field("subcategory")  String subcategory,
            @Field("title")  String title,
            @Field("details")  String details,
            @Field("skills")  String skills,
            @Field("types")  String types,
            @Field("rate")  String rate,
            @Field("duration")  String duration,
            @Field("visibility")  String visibility,
            @Field("featured")  String featured
    );

    //postHireBy
    @FormUrlEncoded
    @POST("postHireBy")
    Call<ApiPostJobResponse>ClientPostHireBy(
            @Field("pid") String Pid,
            @Field("hireby")  String hireby
    );


    //getAll Posted Jobs
    @GET("allpostjobs/{pageNo}")
    Call<ApiPostJobResponse>ClientPostAJobRead(@Path("pageNo") String pageNo);

    //getAll Posted Jobs
    @GET("alljobswithclient/{pageNo}")
    Call<ApiPostJobResponse>withClientJobRead(@Path("pageNo") int pageNo);

    //getAll Posted Jobs by Id
    @GET("getJobbyId/{id}")
    Call<ApiPostJobResponse>getPostJobbyid(@Path("id") String id);

    //Delete Posted Job
    @FormUrlEncoded
    @POST("postjobdelete")
    Call<ApiPostJobResponse>ClientPostAJobDelete(
            @Field("pid") String Pid,
            @Field("uid") String Uid,
            @Field("cid")  String cid
    );

// POSTING CONTEST -------------------------------------------------------/

    //Post New Contest
    @FormUrlEncoded
    @POST("postcontest")
    Call<ApiPostContestResponse>ClientPostContest(
            @Field("uid") String Uid,
            @Field("cid")  String cid,
            @Field("title")  String title,
            @Field("description")  String description,
            @Field("duration")  String duration,
            @Field("prizemoney")  String prizemoney,
            @Field("mode")  String mode,
            @Field("types")  String types
    );

    //Update Posted Contest
    @FormUrlEncoded
    @POST("postcontestUpdate")
    Call<ApiPostContestResponse>ClientPostContestUpdate(
            @Field("pid") String Pid,
            @Field("uid") String Uid,
            @Field("cid")  String cid,
            @Field("title")  String title,
            @Field("description")  String description,
            @Field("duration")  String duration,
            @Field("prizemoney")  String prizemoney,
            @Field("mode")  String mode,
            @Field("types")  String types
    );


    //getAll Posted Contest
    @GET("allpostcontest/{pageNo}")
    Call<ApiPostContestResponse>ClientPostContestRead(@Path("pageNo") int pageNo);

    //Delete Posted Contest
    @FormUrlEncoded
    @POST("postcontestdelete")
    Call<ApiPostContestResponse>ClientPostContestDelete(
            @Field("pid") String Pid,
            @Field("uid") String Uid,
            @Field("cid")  String cid
    );

    // SKILLS SET -------------------------------------------------------/
    //get all Skills
    @GET("allSkills")
    Call<List<ApiSkillsResponse>> getAllSkills();

    //Add Lancers Skills
    @FormUrlEncoded
    @POST("addLancerSkill")
    Call<ApiResponse>ClientPostContestDelete(
            @Field("uid") String uid,
            @Field("lid") String lid,
            @Field("category") String category,
            @Field("subcategory") String subcategory,
            @Field("skill") String skill,
            @Field("selfintro")  String selfintro
    );


    //Getall Small suggestions when a category is searched
    @FormUrlEncoded
    @POST("getSmallSuggestions")
    Call<List<ApiSkillsResponse>>getSmallSuggestion(
            @Field("title") String Title);


    //Profile Count
    //Check if client exist
    @GET("getProfileStatus/{uid}")
    Call<ApiProfileStatus>getProfileStatus(@Path("uid") String uid);


    //Serach Jobs
    @FormUrlEncoded
    @POST("jobsearch")
    Call<ApiPostJobResponse>JobsSearch(@Field("pageNo") int pageNo, @Field("title") CharSequence Title);

/**********************BIDS SECTION *******************/
    //Serach Bids by Ids
    @FormUrlEncoded
    @POST("getbidsbyid")
    Call<List<ApiBidsResponse>>getBidsById(@Field("uid") String uid, @Field("lid") String lid);

    //All Bids
    @GET("getbids")
    Call<List<ApiBidsResponse>>getBids();

       //Serach Bids by Ids
    @FormUrlEncoded
    @POST("addbids")
    Call<List<ApiBidsResponse>>AddBids(@Field("uid") String uid, @Field("lid") String lid, @Field("bids") int Bids);

    //Serach Bids by Ids
    @FormUrlEncoded
    @POST("increasebids")
    Call<ApiBidsResponse>IncreaseBids(@Field("uid") String uid, @Field("lid") String lid, @Field("bids") int Bids);

    //Serach Bids by Ids
    @FormUrlEncoded
    @POST("decreasebids")
    Call<ApiBidsResponse>DecreaseBids(@Field("uid") String uid, @Field("lid") String lid, @Field("bids") int Bids);


    /**********************POST QUOTE OR SUBMIT PROPOSAL SECTION *******************/

    //proposal Insertion(C)
    @FormUrlEncoded
    @POST("addproposal")
    Call<ApiProposalResponse>addProposal(@Field("cid") String cid, @Field("lid") String lid, @Field("jobid") String jobId);


    //proposal Milestone Insertion(C)
    @Headers({"Content-Type:application/json"})
    @POST("addmilestone")
    Call<ApiProposalResponse> addMilestones(@Body MilestonesModel milestonesModel);

    //proposal CoverLetter Insertion(C)
    @FormUrlEncoded
    @POST("addCoverletter")
    Call<ApiProposalResponse>addCoverLetter(@Field("pid") String uid,  @Field("coverletter") String coverLetter);

    // Post All Proposal Master Reading(R) per Lancer
    @FormUrlEncoded
    @POST("getProposalbyid")
    Call<List<ApiGetProposals>>getProposalbyLid(@Field("lid") String lid);

    // Post Proposal Milestones Reading(R)
    @GET("getmilestonebyid/{pid}")
    Call<List<MilestonesModel.Detail>>getMilestones(@Path("pid") String pid);

    // Post Proposal CoverLetter Reading(R)
    @GET("getcoverletterbyid/{pid}")
    Call<List<MilestonesModel.Detail>>getCoverletter(@Path("pid") String pid);

    //proposal Milestone Updation(U)
    @Headers({"Content-Type:application/json"})
    @POST("updatemilestone")
    Call<ApiProposalResponse> updateMilestones(@Body MilestonesModel milestonesModel);

    //proposal CoverLetter Updation(U)
    @FormUrlEncoded
    @POST("updateCoverletter")
    Call<ApiProposalResponse>updateCoverLetter(@Field("pid") String pid,  @Field("coverletter") String coverLetter);

    // Post Proposal CoverLetter Reading(R)
    @GET("getProposalforclient/{cid}")
    Call<List<ApiClientProposalsResponse>>getClientProposal(@Path("cid") String cid);

}

