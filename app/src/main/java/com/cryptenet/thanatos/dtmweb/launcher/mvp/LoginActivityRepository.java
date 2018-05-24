/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.launcher.mvp;

import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerActivity
public class LoginActivityRepository extends BaseRepository
        implements LoginActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(LoginActivityRepository.class);
    private List<Projects> projectsList;

    public LoginActivityRepository() {
        this.projectsList = new ArrayList<>();
    }

    @Override
    public boolean validateLogin(String email, String password, int type) {
        String token = (type == 1) ? ConstantProvider.ACCESS_TOKEN_INVE : ConstantProvider.ACCESS_TOKEN_INIT;
        Call<AllPlansResponse> req2 = client.getAllPlans("Bearer " + token);

        req2.enqueue(new Callback<AllPlansResponse>() {
            @Override
            public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
                AllPlansResponse allPlansResponse = response.body();
                assert allPlansResponse != null;
                setProjects(allPlansResponse.getResults());
            }

            @Override
            public void onFailure(Call<AllPlansResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: AllPlansResponse");
            }
        });
        return false;
    }

    private void setProjects(List<Projects> projectsList) {
        this.projectsList = projectsList;

        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsList));
    }
}
