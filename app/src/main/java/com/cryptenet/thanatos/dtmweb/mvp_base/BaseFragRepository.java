/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_base;

import com.cryptenet.thanatos.dtmweb.http.ApiClient;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseFragContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseFragRepository implements BaseFragContract.Repository {
    protected ApiClient client;

    public BaseFragRepository() {
        if (client == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(ConstantProvider.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            client = retrofit.create(ApiClient.class);
        }
    }
}
