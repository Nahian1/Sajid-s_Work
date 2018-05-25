/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list.mvp;

import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

@PerFragment
public class PlanListFragmentRepository extends BaseFragRepository
        implements PlanListFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(PlanListFragmentRepository.class);
    private List<Projects> projectsList;

    public PlanListFragmentRepository() {
        this.projectsList = new ArrayList<>();
    }

    public void getAllProjects() {
//        Call<AllPlansResponse> req = client.getAllPlans("Bearer " + ConstantProvider.ACCESS_TOKEN_INIT);
//        req.enqueue(new Callback<AllPlansResponse>() {
//            @Override
//            public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
//                AllPlansResponse allPlansResponse = response.body();
//                try {
//                    assert allPlansResponse != null;
//                    setProjects(allPlansResponse.getResults());
//                } catch (NullPointerException ex) {
//                    ex.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<AllPlansResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: AllPlansResponse");
//            }
//        });
    }

    private void setProjects(List<Projects> projectsList) {
        this.projectsList = projectsList;
        for (Projects projects : projectsList)
            Log.d(TAG, "setProjects: " + projects.getTitle());
        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsList));
    }
}
