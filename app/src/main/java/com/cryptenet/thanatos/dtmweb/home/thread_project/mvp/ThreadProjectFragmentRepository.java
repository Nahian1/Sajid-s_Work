/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadProjectFragmentRepository extends BaseFragRepository
        implements ThreadProjectFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(ThreadProjectFragmentRepository.class);

    @Override
    public void getInvestorThreads(int planId, Context context) {
//        Call<ThreadDistinctResponse> req = apiClient.getThreadInv("Bearer " +
//                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null), planId);
//
//        req.enqueue(new Callback<ThreadDistinctResponse>() {
//            @Override
//            public void onResponse(Call<ThreadDistinctResponse> call, Response<ThreadDistinctResponse> response) {
//                ThreadDistinctResponse allPlansResponse = response.body();
//                assert allPlansResponse != null;
//                setAllThreadsInv(allPlansResponse.getResults());
//            }
//
//            @Override
//            public void onFailure(Call<ThreadDistinctResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: AllPlansResponse");
//            }
//        });
    }


//    private void setAllThreadsInv(List<>)
}
