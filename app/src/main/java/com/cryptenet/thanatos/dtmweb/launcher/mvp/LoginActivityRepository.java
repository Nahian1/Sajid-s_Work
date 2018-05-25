/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.launcher.mvp;

import android.util.Base64;

import com.cryptenet.thanatos.dtmweb.borrowed.PostAsync;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class LoginActivityRepository extends BaseRepository
        implements LoginActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(LoginActivityRepository.class);

    public LoginActivityRepository() {

    }

    @Override
    public boolean validateLogin(String email, String password) {
        byte[] data =
                (ConstantProvider.CLIENT_ID +
                ":" +
                ConstantProvider.CLIENT_SECRET).getBytes();

        String s = "Basic " + Base64.encodeToString(
                        data, Base64.DEFAULT
        );

        String payload = "{\"username\": \"" + email +"\", \"password\": \"" + password + "\", \"granttype\": \"password\"}";

        PostAsync async = new PostAsync();
        async.execute(
                "2",
                payload,
                email,
                password,
                s
        );
//        Call<ResponseBody> req = client.getLogin(s, new Login(email, password));
//        req.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d(TAG, "onResponse: " + response.toString());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(TAG, "onFailure: attempt");
//            }
//        });

        return false;
    }
}
