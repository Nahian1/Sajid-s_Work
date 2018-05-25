/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.http;

import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.CityResponse;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;

import java.io.File;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("api/v1/country")
    Call<CountryResponse> getCountries();

    @GET("api/v1/city")
    Call<CityResponse> getCities(@Query("country") int countryCode);

    @FormUrlEncoded
    @POST("api/v1/user")
    Call<ResponseBody> attemptReg(
            @Field("picture")File picture,
            @Field("user_type") String user_type,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address,
            @Field("country") String country,
            @Field("city") String city,
            @Field("bank_name") String bank_name,
            @Field("bank_account_name") String bank_account_name,
            @Field("bank_account_number") String bank_account_number
    );

    @GET("api/v1/plan/")
    Call<AllPlansResponse> getAllPlans(@Header("Authorization") String token);


    @POST("o/token/")
    Call<ResponseBody> getLogin(@Header("Authorization") String token, RequestBody body);
}
