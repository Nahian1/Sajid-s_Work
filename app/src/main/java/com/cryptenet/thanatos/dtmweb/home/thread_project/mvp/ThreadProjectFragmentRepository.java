/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ThreadProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.MessageThreadModel;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadInv;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Call<MessageThreadModel> threadCall = apiClient.getThreads(
                "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).
                        getString(ConstantProvider.SP_ACCESS_TOKEN, null), planId);

        threadCall.enqueue(new Callback<MessageThreadModel>() {
            @Override
            public void onResponse(Call<MessageThreadModel> call, Response<MessageThreadModel> response) {

                //  response.body().toString();

                if (response.isSuccessful()){

                    if (response.body().getResults().length > 0){
                        //Toast.makeText(context, " Plan founds", Toast.LENGTH_SHORT).show();
                        ThreadInv[] results = response.body().getResults();
                        EventBus.getDefault().post(new ThreadProjectListReceiveEvent(results));
                    }else {

                        Toast.makeText(context, "No Plan founds", Toast.LENGTH_SHORT).show();
                    }
                }else {


                }
            }

            @Override
            public void onFailure(Call<MessageThreadModel> call, Throwable t) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }


//    private void setAllThreadsInv(List<>)

}
