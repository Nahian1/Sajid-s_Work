/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_base;

import android.content.SharedPreferences;

import com.cryptenet.thanatos.dtmweb.http.ApiClient;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRepository implements BaseContract.Repository {
    protected ApiClient apiClient;

    @Inject
    protected SharedPreferences preferences;

    public BaseRepository() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .connectTimeout(180, TimeUnit.SECONDS)
//                .readTimeout(180, TimeUnit.SECONDS)
//                .writeTimeout(180, TimeUnit.SECONDS)
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantProvider.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);
    }
}
