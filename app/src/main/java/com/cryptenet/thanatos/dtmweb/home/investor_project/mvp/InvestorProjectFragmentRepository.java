/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ManageProjectReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InvestorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.PlanAccessResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Plans;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class InvestorProjectFragmentRepository extends BaseFragRepository
        implements InvestorProjectFragmentContract.Repository {
    //    private static String TAG = TagProvider.getDebugTag(InvestorProjectFragmentRepository.class);
    private List<Plans> projectsRspList;

    public InvestorProjectFragmentRepository() {
        this.projectsRspList = new ArrayList<>();
    }

    @Override
    public void getMyProjectList(int reqType, Context context, int offset) {
        if (reqType == 2) {
            Call<PlanAccessResponse> req = apiClient.getAllMyReqInv("Bearer " +
                            PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                    50,
                    offset
            );
            req.enqueue(new Callback<PlanAccessResponse>() {
                @Override
                public void onResponse(Call<PlanAccessResponse> call, Response<PlanAccessResponse> response) {
                    if (response.isSuccessful()) {
                        PlanAccessResponse allPlansResponse = response.body();
                        assert allPlansResponse != null;
                        setProjects(allPlansResponse.getResults());
                        if (response.body().getResults().size() == 0 && offset == 0)
                            Toast.makeText(context, context.getString(R.string.no_man_requests), Toast.LENGTH_LONG).show();
                        else if (response.body().getResults().size() == 0 && offset > 0)
                            Toast.makeText(context, context.getString(R.string.no_more_man_requests), Toast.LENGTH_LONG).show();
                    } else {
//                        Log.d(TAG, "onResponse: " + response.code());
                        EventBus.getDefault().post(new RequestFailureEvent(true));
                    }
                }

                @Override
                public void onFailure(Call<PlanAccessResponse> call, Throwable t) {
//                    Log.d(TAG, "onFailure: AllPlansResponse");
                    EventBus.getDefault().post(new RequestFailureEvent(true));
                }
            });
        } else {
            Call<PlanAccessResponse> req = apiClient.getAllMyReqInvApr(
                    "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                    50,
                    offset,
                    true
            );
            req.enqueue(new Callback<PlanAccessResponse>() {
                @Override
                public void onResponse(Call<PlanAccessResponse> call, Response<PlanAccessResponse> response) {
                    if (response.isSuccessful()) {
                        PlanAccessResponse allPlansResponse = response.body();
                        assert allPlansResponse != null;
                        setProjects(allPlansResponse.getResults());

                        if (response.body().getResults().size() == 0 && offset == 0)
                            Toast.makeText(context, context.getString(R.string.no_man_projects), Toast.LENGTH_LONG).show();
                        else if (response.body().getResults().size() == 0 && offset > 0)
                            Toast.makeText(context, context.getString(R.string.no_more_man_projects), Toast.LENGTH_LONG).show();
                    } else {
//                        Log.d(TAG, "onResponse: " + response.code());
                        EventBus.getDefault().post(new RequestFailureEvent(true));
                    }
                }

                @Override
                public void onFailure(Call<PlanAccessResponse> call, Throwable t) {
//                    Log.d(TAG, "onFailure: AllReqPlansResponse");
                    EventBus.getDefault().post(new RequestFailureEvent(true));
                }
            });
        }
    }

    private void setProjects(List<Plans> projectsRspList) {
        this.projectsRspList = projectsRspList;
//        for (Plans projectsRsp : projectsRspList)
//            Log.d(TAG, "setProjects: " + projectsRsp.getPlanTitle());
        EventBus.getDefault().post(new ManageProjectReceiveEvent(projectsRspList));
    }
}
