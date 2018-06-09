/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantProvider.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClient = retrofit.create(ApiClient.class);
    }
}
