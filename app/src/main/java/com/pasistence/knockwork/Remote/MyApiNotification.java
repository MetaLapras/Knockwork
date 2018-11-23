package com.pasistence.knockwork.Remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MyApiNotification {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AIzaSyCXVn8mAA1CtwfkTCjwFbjA-kltHzqNbcA"})
    @POST("fcm/send")
    Call<String> notificattion(@Body com.pasistence.knockwork.Model.ApiResponse.ApiNotification notification);

}
