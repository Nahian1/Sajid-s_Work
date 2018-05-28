/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InvestorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class InvestorProjectFragmentRepository extends BaseFragRepository
        implements InvestorProjectFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(InvestorProjectFragmentRepository.class);
    private List<ProjectsRsp> projectsRspList;

    public InvestorProjectFragmentRepository() {
        this.projectsRspList = new ArrayList<>();
    }

    @Override
    public void getMyProjectList(int reqType, Context context) {
        if (reqType == 1) {
            Call<AllPlansResponse> req = apiClient.getAllMyReqInv("Bearer " +
                    PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null));
            req.enqueue(new Callback<AllPlansResponse>() {
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
        } else {
            Call<AllPlansResponse> req = apiClient.getAllReqPlansINT("Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null));
            req.enqueue(new Callback<AllPlansResponse>() {
                @Override
                public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
                    AllPlansResponse allPlansResponse = response.body();
                    assert allPlansResponse != null;
                    setProjects(allPlansResponse.getResults());
                }

                @Override
                public void onFailure(Call<AllPlansResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: AllReqPlansResponse");
                }
            });
        }
    }

    private void setProjects(List<ProjectsRsp> projectsRspList) {
        this.projectsRspList = projectsRspList;
        for (ProjectsRsp projectsRsp : projectsRspList)
            Log.d(TAG, "setProjects: " + projectsRsp.getTitle());
        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsRspList));
    }
}
