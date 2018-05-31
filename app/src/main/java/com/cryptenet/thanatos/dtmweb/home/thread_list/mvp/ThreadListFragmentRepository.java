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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadInitResponse;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class ThreadListFragmentRepository extends BaseFragRepository
        implements ThreadListFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(ThreadListFragmentRepository.class);

    @Override
    public void getThreadList(Context context) {

        Call<ThreadInitResponse> req = apiClient.getThreadList("Bearer " +
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null));

        req.enqueue(new Callback<ThreadInitResponse>() {
            @Override
            public void onResponse(Call<ThreadInitResponse> call, Response<ThreadInitResponse> response) {
                ThreadInitResponse allPlansResponse = response.body();
                assert allPlansResponse != null;
//                setProjects(allPlansResponse.getResults());
            }

            @Override
            public void onFailure(Call<ThreadInitResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: AllPlansResponse");
            }
        });
    }
}
