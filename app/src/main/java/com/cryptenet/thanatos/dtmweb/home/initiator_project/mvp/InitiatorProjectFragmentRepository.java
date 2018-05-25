/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project.mvp;

import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InitiatorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

@PerFragment
public class InitiatorProjectFragmentRepository extends BaseFragRepository
        implements InitiatorProjectFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(InitiatorProjectFragmentRepository.class);
    private List<Projects> projectsList;

    public InitiatorProjectFragmentRepository() {
        this.projectsList = new ArrayList<>();
    }

    @Override
    public void getMyProjectList(int reqType) {

//        if (reqType == 1) {
//            Call<AllPlansResponse> req = client.getAllMyPlans("Bearer " + ConstantProvider.ACCESS_TOKEN_INIT);
//            req.enqueue(new Callback<AllPlansResponse>() {
//                @Override
//                public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
//                    AllPlansResponse allPlansResponse = response.body();
//                    assert allPlansResponse != null;
//                    setProjects(allPlansResponse.getResults());
//                }
//
//                @Override
//                public void onFailure(Call<AllPlansResponse> call, Throwable t) {
//                    Log.d(TAG, "onFailure: AllPlansResponse");
//                }
//            });
//        } else {
//            Call<AllPlansResponse> req = client.getAllReqPlansINT("Bearer " + ConstantProvider.ACCESS_TOKEN_INIT);
//            req.enqueue(new Callback<AllPlansResponse>() {
//                @Override
//                public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
//                    AllPlansResponse allPlansResponse = response.body();
//                    assert allPlansResponse != null;
//                    setProjects(allPlansResponse.getResults());
//                }
//
//                @Override
//                public void onFailure(Call<AllPlansResponse> call, Throwable t) {
//                    Log.d(TAG, "onFailure: AllReqPlansResponse");
//                }
//            });
//        }
    }

    private void setProjects(List<Projects> projectsList) {
        this.projectsList = projectsList;
        for (Projects projects : projectsList)
            Log.d(TAG, "setProjects: " + projects.getTitle());
        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsList));
    }
}
