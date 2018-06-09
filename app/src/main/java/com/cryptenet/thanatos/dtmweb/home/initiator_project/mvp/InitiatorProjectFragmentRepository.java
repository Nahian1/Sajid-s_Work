/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ManageProjectReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InitiatorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.PlanAccessResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Plans;
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
public class InitiatorProjectFragmentRepository extends BaseFragRepository
        implements InitiatorProjectFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(InitiatorProjectFragmentRepository.class);
    private List<ProjectsRsp> projectsRspList;
    private List<Plans> plansList;

    public InitiatorProjectFragmentRepository() {
        this.projectsRspList = new ArrayList<>();
    }

    @Override
    public void getMyProjectList(int reqType, Context context, int offset) {

        if (reqType == 1) {
            Call<AllPlansResponse> req = apiClient.getAllMyPlans("Bearer " +
                    PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null));
            req.enqueue(new Callback<AllPlansResponse>() {
                @Override
                public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
                    if (response.isSuccessful()) {
                        AllPlansResponse allPlansResponse = response.body();
                        assert allPlansResponse != null;
                        setProjects(allPlansResponse.getResults());
                        if (allPlansResponse.getResults().size() == 0)
                            Toast.makeText(context, context.getString(R.string.no_man_projects), Toast.LENGTH_LONG).show();
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<AllPlansResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: AllPlansResponse");
                }
            });
        } else {
            Call<PlanAccessResponse> req = apiClient.getAllReqPlansINT(
                    "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null),
                    50,
                    offset
            );
            req.enqueue(new Callback<PlanAccessResponse>() {
                @Override
                public void onResponse(Call<PlanAccessResponse> call, Response<PlanAccessResponse> response) {
                    if (response.isSuccessful()) {
                        PlanAccessResponse allPlansResponse = response.body();
                        assert allPlansResponse != null;
                        setManageProjects(allPlansResponse.getResults());

                        if (response.body().getResults().size() == 0 && offset == 0)
                            Toast.makeText(context, context.getString(R.string.no_man_requests), Toast.LENGTH_LONG).show();
                        else if (response.body().getResults().size() == 0 && offset > 0)
                            Toast.makeText(context, context.getString(R.string.no_more_man_requests), Toast.LENGTH_LONG).show();
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PlanAccessResponse> call, Throwable t) {
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

    private void setManageProjects(List<Plans> projectsRspList) {
        this.plansList = projectsRspList;
        for (Plans projectsRsp : projectsRspList)
            Log.d(TAG, "setProjects: " + projectsRsp.getPlanTitle());
        EventBus.getDefault().post(new ManageProjectReceiveEvent(this.plansList));
    }
}
