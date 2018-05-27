/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list.mvp;

import android.content.Context;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.http.ApiClient;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

@PerFragment
public class PlanListFragmentRepository extends BaseFragRepository
        implements PlanListFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(PlanListFragmentRepository.class);
    private List<Projects> projectsList;

    public PlanListFragmentRepository() {
        this.projectsList = new ArrayList<>();
    }

    @Override
    public void getAllProjects(Context context, String token) {
        String head = "application/json";
        String oauth = token;
                //PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getString(ConstantProvider.SP_ACCESS_TOKEN, null);
//        Log.d(TAG, "getAllProjects: " + token);
//        if (oauth != null) {
//
//
//            OkHttpClient client = new OkHttpClient();
//
//            final Request request = new Request.Builder()
//                    .url(ConstantProvider.BASE_URL + "api/v1/plan/")
//                    .get()
//                    .addHeader("Content-Type", head)
//                    .build();
//            Log.d(TAG, "getAllProjects: " + request.header("Authentication"));
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    Log.d(TAG, "onFailure: plan list");
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    try {
//                        Log.d(TAG, "onResponse: " + response.body().string());
//                        Gson gson = new Gson();
//                        AllPlansResponse allPlansResponse = gson.fromJson(response.body().string(), AllPlansResponse.class);
//                        EventBus.getDefault().post(new ProjectListReceiveEvent(allPlansResponse.getResults()));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//        }
        Call<AllPlansResponse> req = apiClient.getAllPlans("Bearer " + token);
        req.enqueue(new Callback<AllPlansResponse>() {
            @Override
            public void onResponse(Call<AllPlansResponse> call, retrofit2.Response<AllPlansResponse> response) {

                AllPlansResponse allPlansResponse = response.body();
                try {
                    assert allPlansResponse != null;
                    setProjects(allPlansResponse.getResults());
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AllPlansResponse> call, Throwable t) {

            }
        });
    }

    private void setProjects(List<Projects> projectsList) {
        this.projectsList = projectsList;
        for (Projects projects : projectsList)
            Log.d(TAG, "setProjects: " + projects.getTitle());
        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsList));
    }
}
