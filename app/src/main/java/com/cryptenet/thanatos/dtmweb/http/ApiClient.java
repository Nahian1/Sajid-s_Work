/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.http;

import com.cryptenet.thanatos.dtmweb.pojo.CityResponse;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("country")
    Call<CountryResponse> getCountries();

    @GET("city")
    Call<CityResponse> getCities(@Query("country") int countryCode);

    @POST("user")
    Call<ResponseBody> attemptReg(@Body RegistrationInput input);

//    @FormUrlEncoded
//    @POST("user")
//    Call<ResponseBody> attemptReg(
//            @Field("picture")File picture,
//            @Field("user_type") String user_type,
//            @Field("name") String name,
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("address") String address,
//            @Field("country") int country,
//            @Field("city") int city,
//            @Field("bank_name") String bank_name,
//            @Field("bank_account_name") String bank_account_name,
//            @Field("bank_account_number") String bank_account_number
//    );

}
