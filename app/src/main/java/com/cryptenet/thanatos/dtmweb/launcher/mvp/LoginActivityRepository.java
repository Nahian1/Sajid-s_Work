/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.launcher.mvp;

import android.util.Base64;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.borrowed.PostAsync;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

@PerActivity
public class LoginActivityRepository extends BaseRepository
        implements LoginActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(LoginActivityRepository.class);
    private List<Projects> projectsList;

    public LoginActivityRepository() {
        this.projectsList = new ArrayList<>();
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


//        req2.enqueue(new Callback<AllPlansResponse>() {
//            @Override
//            public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
//                AllPlansResponse allPlansResponse = response.body();
//                assert allPlansResponse != null;
//                setProjects(allPlansResponse.getResults());
//            }
//
//            @Override
//            public void onFailure(Call<AllPlansResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: AllPlansResponse");
//            }
//        });
        return false;
    }

    private void setProjects(List<Projects> projectsList) {
        this.projectsList = projectsList;
        for (Projects projects : projectsList)
            Log.d(TAG, "setProjects: " + projects.getTitle());
        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsList));
    }
}
