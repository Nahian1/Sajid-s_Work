/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.http;

import com.cryptenet.thanatos.dtmweb.pojo.AllCategoriesResponse;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.CityResponse;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;
import com.cryptenet.thanatos.dtmweb.pojo.IssueResponse;
import com.cryptenet.thanatos.dtmweb.pojo.MessageThreadModel;
import com.cryptenet.thanatos.dtmweb.pojo.PlanAccessResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadDistinctResponse;
import com.cryptenet.thanatos.dtmweb.pojo.TransactionDetails;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileResponse;
import com.cryptenet.thanatos.dtmweb.pojo.message_model.MessageListModel;
import com.cryptenet.thanatos.dtmweb.pojo.message_model.SendMessageModel;
import com.cryptenet.thanatos.dtmweb.pojo.message_model.ThreadRequestModel;


import okhttp3.MultipartBody;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;

import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import retrofit2.http.Query;
import rx.Observable;

public interface ApiClient {
//    @GET("api/v1/country/")
//    Call<CountryResponse> getCountries();

    @GET("api/v1/plan-category/")
    Call<AllCategoriesResponse> getCategories(@Header("Authorization") String token);

//    @GET("api/v1/city/")
//    Call<CityResponse> getCities(@Query("country") int countryCode);
//
//    @FormUrlEncoded
//    @POST("api/v1/user")
//    Call<ResponseBody> attemptReg(
//            @Field("picture")File picture,
//            @Field("user_type") String user_type,
//            @Field("name") String name,
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("address") String address,
//            @Field("country") String country,
//            @Field("city") String city,
//            @Field("bank_name") String bank_name,
//            @Field("bank_account_name") String bank_account_name,
//            @Field("bank_account_number") String bank_account_number
//    );

    @GET("api/v1/plan/")
    Call<AllPlansResponse> getAllPlans(@Header("Authorization") String token);

    @GET("api/v1/plan/my/")
    Call<AllPlansResponse> getAllMyPlans(@Header("Authorization") String token);

    @GET("api/v1/plan/")
    Call<AllPlansResponse> getAllMyPlansSearch(@Header("Authorization") String token, @Query("search") String searchTerm);

    @GET("/api/v1/plan-access/")
    Call<PlanAccessResponse> getAllMyReqInv(@Header("Authorization") String token);

    @GET("/api/v1/plan-access/?is_approved=True")
    Call<PlanAccessResponse> getAllMyReqInvApr(@Header("Authorization") String token);

    @GET("api/v1/plan-access/")
    Call<PlanAccessResponse> getAllReqPlansINT(@Header("Authorization") String token);

    @GET("/api/v1/issue/categories/")
    Call<IssueResponse> getAllIssues(@Header("Authorization") String token);

    @GET("api/v1/message-thread/distinct/")
    Call<ThreadDistinctResponse> getThreadList(@Header("Authorization") String token);

    @GET("/api/v1/message-thread/")
    Call<ThreadDistinctResponse> getThreadInv(@Header("Authorization") String token);

    @GET("/api/v1/plan-access/{id}")
    Call<TransactionDetails> getTransactionDetails(@Header("Authorization") String token, @Path("id") int transactionId);

    @GET("/api/v1/user/{id}")
    Call<User> getUserData(@Header("Authorization") String token, @Path("id") int transactionId);

//    @FormUrlEncoded
//    @POST("/api/v1/plan-access/")
//    Call<TransactionDetails> submitTransaction(
//            @Header("Authorization") String token,
//            @FieldMap Map<String, String> map
//    );

    @POST("/api/v1/plan-access/")
    Call<TransactionDetails> submitTransaction(
            @Header("Authorization") String token,
            RequestBody formBody
    );


//    @GET("GET /api/v1/plan/{id}")
//    Call<ProjectsDSResponse> getShortDesc(@Header("Authorization") String token, @Path("id") int id);
//
//    @GET("GET /api/v1/plan/{id}/details")
//    Call<ResponseBody> getLongDesc(@Header("Authorization") String token, @Path("id") int id);

    @Multipart
    @POST("api/v1/user/")
    Call<RegistrationResponse> createNewUser(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,

            @Part MultipartBody.Part picture,

            @Part("city") RequestBody city,
            @Part("country") RequestBody country,
            @Part("bank_name") RequestBody bank_name,
            @Part("bank_account_name") RequestBody bank_account_name,
            @Part("bank_account_number") RequestBody bank_account_number,
            @Part("user_type") RequestBody user_type,
            @Part("address") RequestBody address
    );

    @Multipart
    @PUT("api/v1/user/{id}/")
    Observable<UpdateProfileResponse> updateUserProfile(
            @Header("Authorization") String token,
            @Path("id") int user_id,

            @Part("name") String name,
            @Part("email") String email,
            @Part("password") String password,

            @Part MultipartBody.Part picture,

            @Part("city") String city,
            @Part("country") String country,
            @Part("bank_name") String bank_name,
            @Part("bank_account_name") String bank_account_name,
            @Part("bank_account_number") String bank_account_number,
            @Part("user_type") String user_type,
            @Part("address") String address
    );

    @GET("api/v1/country/")
    Call<CountryResponse> getAllcountries();

    @GET("api/v1/city/")
    Call<CityResponse> getAllCities(
            @Query("country") int countryCode
    );

    @Multipart
    @POST("/api/v1/plan/")
    Observable<ProjectsRsp> crerateNewInitiatorPlan(
            @Header("Authorization") String token,
            @Part("title") String title,
            @Part("category") String category,
            @Part("short_description") String short_description,
            @Part("long_description") String long_description,
            @Part("minimum_investment_cost") String minimum_investment_cost,
            @Part("maximum_investment_cost") String maximum_investment_cost,
            @Part("access_price") String access_price,

            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part uploaded_file
    );

    @GET("api/v1/message-thread/")
    Call<MessageThreadModel> getThreads(@Header("Authorization") String token , @Query("plan") int planId);

    @FormUrlEncoded
    @POST("api/v1/message/")
    Call<SendMessageModel> sendMessage(@Header("Authorization") String token , @Field("thread") String threadId , @Field("text") String text);

    @GET("api/v1/message-thread/{thread_id}/messages/")
    Call<MessageListModel> getMessages(@Header("Authorization") String token , @Path("thread_id") String threadId);

    @FormUrlEncoded
    @POST("api/v1/message-thread/")
    Call<ThreadRequestModel> getThreadID(@Header("Authorization") String token , @Field("plan") int planId);
}
