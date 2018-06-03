/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.http;

import com.cryptenet.thanatos.dtmweb.pojo.AllCategoriesResponse;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.IssueResponse;
import com.cryptenet.thanatos.dtmweb.pojo.PlanAccessResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadDistinctResponse;
import com.cryptenet.thanatos.dtmweb.pojo.TransactionDetails;
import com.cryptenet.thanatos.dtmweb.pojo.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<ThreadDistinctResponse> getThreadInv(@Header("Authorization") String token, @Query("plan") int planId);

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
}
