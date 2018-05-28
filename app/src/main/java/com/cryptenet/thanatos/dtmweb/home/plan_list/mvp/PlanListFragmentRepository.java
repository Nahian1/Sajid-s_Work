/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

@PerFragment
public class PlanListFragmentRepository extends BaseFragRepository
        implements PlanListFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(PlanListFragmentRepository.class);
    private List<ProjectsRsp> projectsRspList;

    public PlanListFragmentRepository() {
        this.projectsRspList = new ArrayList<>();
    }

    @Override
    public void getAllProjects(Context context, String token) {

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

    @Override
    public int checkUserType(Context context) {
        String user = PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_USER_TYPE, null);

        if (user.equals("Initiator"))
            return 2;
        else
            return 1;
    }

    private void setProjects(List<ProjectsRsp> projectsRspList) {
        this.projectsRspList = projectsRspList;
        for (ProjectsRsp projectsRsp : projectsRspList)
            Log.d(TAG, "setProjects: " + projectsRsp.getTitle());
        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsRspList));
    }
}
