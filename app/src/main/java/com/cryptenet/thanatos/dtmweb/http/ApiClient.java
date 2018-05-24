/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.http;

import com.cryptenet.thanatos.dtmweb.pojo.CityResponse;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("country")
    Call<CountryResponse> getCountries();

    @GET("city")
    Call<CityResponse> getCities(@Query("country") int countryCode);

    @Multipart
    @POST("user")
    Call<ResponseBody> attemptReg(
            @Part MultipartBody.Part pp,
            @Part("user_type") RequestBody user_type,
            @Part("name") RequestBody name,
            @Part("name") RequestBody email,
            @Part("name") RequestBody password,
            @Part("name") RequestBody address,
            @Part("name") RequestBody country,
            @Part("name") RequestBody city,
            @Part("name") RequestBody bank_name,
            @Part("name") RequestBody bank_account_name,
            @Part("name") RequestBody bank_account_number
    );

}
