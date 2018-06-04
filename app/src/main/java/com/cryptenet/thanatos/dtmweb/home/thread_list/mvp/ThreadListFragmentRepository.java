/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_list.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.DistinctThreadsReceived;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadDistinctResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class ThreadListFragmentRepository extends BaseFragRepository
        implements ThreadListFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(ThreadListFragmentRepository.class);
    List<ThreadIdentity> threadIdentities;

    public ThreadListFragmentRepository() {
        this.threadIdentities = new ArrayList<>();
    }

    @Override
    public void getThreadList(Context context) {
        if (PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_USER_TYPE, null).equals("Initiator")) {
            Call<ThreadDistinctResponse> req = apiClient.getThreadList("Bearer " +
                    PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null));


            req.enqueue(new Callback<ThreadDistinctResponse>() {
                @Override
                public void onResponse(Call<ThreadDistinctResponse> call, Response<ThreadDistinctResponse> response) {

                    if (response.isSuccessful()) {
                        ThreadDistinctResponse allPlansResponse = response.body();
                        assert allPlansResponse != null;
                        setAllThreads(allPlansResponse.getResults());
                    }
                }

                @Override
                public void onFailure(Call<ThreadDistinctResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: AllPlansResponse");
                }
            });
        } else {
            Call<ThreadDistinctResponse> req = apiClient.getThreadInv("Bearer " +
                    PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null));


            req.enqueue(new Callback<ThreadDistinctResponse>() {
                @Override
                public void onResponse(Call<ThreadDistinctResponse> call, Response<ThreadDistinctResponse> response) {

                    if (response.isSuccessful()) {
                        ThreadDistinctResponse allPlansResponse = response.body();
                        assert allPlansResponse != null;
                        setAllThreads(allPlansResponse.getResults());
                    }
                }

                @Override
                public void onFailure(Call<ThreadDistinctResponse> call, Throwable t) {
                    Log.d(TAG, "onFailure: AllPlansResponse");
                }
            });
        }
    }

    public void setAllThreads(List<ThreadIdentity> threadIdentities) {
        this.threadIdentities = threadIdentities;

        EventBus.getDefault().post(new DistinctThreadsReceived(this.threadIdentities));
    }
}
