/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_desc.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ShowPlanDetailsEvent;
import com.cryptenet.thanatos.dtmweb.events.ThreadIdReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDLResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDSResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadInitResponse;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@PerFragment
public class PlanDescFragmentRepository extends BaseFragRepository
        implements PlanDescFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(PlanDescFragmentRepository.class);

    @Override
    public void getLongDetails(Context context, int id) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/plan/" + id + "/details")
                .get()
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Bearer " + PreferenceManager
                        .getDefaultSharedPreferences(context)
                        .getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ProjectsDLResponse detailed = gson.fromJson(response.body().string(), ProjectsDLResponse.class);
//                Log.d(TAG, "onResponse: " + detailed.toString());

                ProjectsDetailed detailed1 = new ProjectsDetailed();
                detailed1.setId(detailed.getId());
                detailed1.setCategoryName(detailed.getCategoryName());
                detailed1.setCover(detailed.getCover());
                detailed1.setTitle(detailed.getTitle());
                detailed1.setCategory(detailed.getCategory());
                detailed1.setMaximumInvestmentCost(detailed.getMaximumInvestmentCost());
                detailed1.setMinimumInvestmentCost(detailed.getMinimumInvestmentCost());
                detailed1.setShortDescription(detailed.getShortDescription());
                detailed1.setLongDescription(detailed.getLongDescription());
                detailed1.setInitiator(detailed.getInitiator());
                detailed1.setInitiatorImage(detailed.getInitiatorImage());
                detailed1.setInitiatorsName(detailed.getInitiatorsName());
                detailed1.setInitiatorAddress(detailed.getInitiatorAddress());
                detailed1.setUploadedFile(detailed.getUploadedFile());
                detailed1.setAccessPrice(detailed.getAccessPrice());
                detailed1.setCreatedAt(detailed.getCreatedAt());
                detailed1.setIsApproved(detailed.getIsApproved());

                EventBus.getDefault().post(new ShowPlanDetailsEvent(detailed1));
            }
        });

//        Call<ResponseBody> detailedCall = apiClient.getLongDesc(
//                "Bearer " + PreferenceManager
//                        .getDefaultSharedPreferences(context)
//                        .getString(ConstantProvider.SP_ACCESS_TOKEN, null),
//                id
//        );
//
//        detailedCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ProjectsDetailed detailed = new ProjectsDetailed();
//                response.body();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void getShortDetails(Context context, int id) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/plan/" + id)
                .get()
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Bearer " + PreferenceManager
                        .getDefaultSharedPreferences(context)
                        .getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                .build();
        Log.d(TAG, " request : " + request.toString());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + "fetch short details failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ProjectsDSResponse detailed = gson.fromJson(response.body().string(), ProjectsDSResponse.class);
                Log.d(TAG, "onResponse: " + detailed.toString());

                ProjectsDetailed detailed1 = new ProjectsDetailed();
                detailed1.setId(detailed.getId());
                detailed1.setCategoryName(detailed.getCategoryName());
                detailed1.setCover(detailed.getCover());
                detailed1.setTitle(detailed.getTitle());
                detailed1.setCategory(detailed.getCategory());
                detailed1.setMaximumInvestmentCost(detailed.getMaximumInvestmentCost());
                detailed1.setMinimumInvestmentCost(detailed.getMinimumInvestmentCost());
                detailed1.setShortDescription(detailed.getShortDescription());
                detailed1.setInitiator(detailed.getInitiator());
                detailed1.setInitiatorsName(detailed.getInitiatorsName());
                detailed1.setUploadedFile(detailed.getUploadedFile());
                detailed1.setAccessPrice(detailed.getAccessPrice());
                detailed1.setCreatedAt(detailed.getCreatedAt());
                detailed1.setIsApproved(detailed.getIsApproved());

                Request request = new Request.Builder()
                        .url(ConstantProvider.BASE_URL + "api/v1/user/" + detailed.getInitiator() + "/")
                        .get()
                        .addHeader("Content-Type", head)
                        .addHeader("Authorization", "Bearer " + PreferenceManager
                                .getDefaultSharedPreferences(context)
                                .getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson = new Gson();
                        User user = gson.fromJson(response.body().string(), User.class);

                        detailed1.setBankName(user.getBankName());
                        detailed1.setBankAccountName(user.getBankAccountName());
                        detailed1.setBankAccountNumber(user.getBankAccountNumber());
                        detailed1.setInitiatorAddress(user.getAddress());

                        EventBus.getDefault().post(new ShowPlanDetailsEvent(detailed1));
                    }
                });

            }
        });
    }

    @Override
    public void getShortDetailsIni(Context context, int id) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/plan/" + id)
                .get()
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Bearer " + PreferenceManager
                        .getDefaultSharedPreferences(context)
                        .getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                .build();
        Log.d(TAG, " request : " + request.toString());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ProjectsDSResponse detailed = gson.fromJson(response.body().string(), ProjectsDSResponse.class);
//                Log.d(TAG, "onResponse: " + detailed.toString());

                ProjectsDetailed detailed1 = new ProjectsDetailed();
                detailed1.setId(detailed.getId());
                detailed1.setCategoryName(detailed.getCategoryName());
                detailed1.setCover(detailed.getCover());
                detailed1.setTitle(detailed.getTitle());
                detailed1.setCategory(detailed.getCategory());
                detailed1.setMaximumInvestmentCost(detailed.getMaximumInvestmentCost());
                detailed1.setMinimumInvestmentCost(detailed.getMinimumInvestmentCost());
                detailed1.setShortDescription(detailed.getShortDescription());
                detailed1.setInitiator(detailed.getInitiator());
                detailed1.setInitiatorsName(detailed.getInitiatorsName());
                detailed1.setUploadedFile(detailed.getUploadedFile());
                detailed1.setAccessPrice(detailed.getAccessPrice());
                detailed1.setCreatedAt(detailed.getCreatedAt());
                detailed1.setIsApproved(detailed.getIsApproved());

                Request request = new Request.Builder()
                        .url(ConstantProvider.BASE_URL + "api/v1/user/" + detailed.getInitiator() + "/")
                        .get()
                        .addHeader("Content-Type", head)
                        .addHeader("Authorization", "Bearer " + PreferenceManager
                                .getDefaultSharedPreferences(context)
                                .getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson = new Gson();
                        User user = gson.fromJson(response.body().string(), User.class);

                        detailed1.setBankName(user.getBankName());
                        detailed1.setBankAccountName(user.getBankAccountName());
                        detailed1.setBankAccountNumber(user.getBankAccountNumber());
                        detailed1.setInitiatorAddress(user.getAddress());

                        EventBus.getDefault().post(new ShowPlanDetailsEvent(detailed1));

                    }
                });

            }
        });
    }

    @Override
    public void getThreadId(Context context, int planId) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("plan", String.valueOf(planId))
                .build();

        Log.d(TAG, "data: " + formBody.toString());

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/message-thread/")
                .post(formBody)
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + "thread id failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                ThreadInitResponse initResponse = gson.fromJson(response.body().string(), ThreadInitResponse.class);
                EventBus.getDefault().post(new ThreadIdReceiveEvent(initResponse));
                Log.d(TAG, "onResponse: thread" + initResponse.toString());
            }
        });
    }
}
