/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
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
import retrofit2.Response;

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
        String head = "application/json";
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
//                        EventBus.getDefault().post(new ProjectListReceiveEvent(allPlansResponse.getIssueParents()));
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

    @Override
    public void searchMyPlans(Context context, String token, String searchTerm) {

        Call<AllPlansResponse> req = apiClient.getAllMyPlansSearch("Bearer " + token, searchTerm);

        req.enqueue(new Callback<AllPlansResponse>() {
            @Override
            public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
                //you got your search result

                EventBus.getDefault().post(new ProjectListReceiveEvent(response.body().getResults()));

            }

            @Override
            public void onFailure(Call<AllPlansResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + "search failed");
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
