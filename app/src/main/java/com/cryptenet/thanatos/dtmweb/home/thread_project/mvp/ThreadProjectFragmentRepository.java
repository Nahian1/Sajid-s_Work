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

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.ThreadProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.MessageThreadModel;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadInv;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class ThreadProjectFragmentRepository extends BaseFragRepository
        implements ThreadProjectFragmentContract.Repository {
//    private static String TAG = TagProvider.getDebugTag(ThreadProjectFragmentRepository.class);

    @Override
    public void getInvestorThreads(int planId, Context context, int offset) {
        Call<MessageThreadModel> threadCall = apiClient.getThreads(
                "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).
                        getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                50,
                offset,
                planId
        );

        threadCall.enqueue(new Callback<MessageThreadModel>() {
            @Override
            public void onResponse(Call<MessageThreadModel> call, Response<MessageThreadModel> response) {
                if (response.isSuccessful()) {
                        List<ThreadInv> results = new ArrayList<>(Arrays.asList(response.body().getResults()));

                        EventBus.getDefault().post(new ThreadProjectListReceiveEvent(results));

                        if (results.size() == 0 && offset == 0)
                            Toast.makeText(context, context.getString(R.string.no_threads_found), Toast.LENGTH_LONG).show();
                        else if (results.size() == 0 && offset > 0)
                            Toast.makeText(context, context.getString(R.string.no_more_threads_found), Toast.LENGTH_LONG).show();
                } else {
//                    Log.d(TAG, "onResponse: " + response.code());
                    EventBus.getDefault().post(new RequestFailureEvent(true));
                }
            }

            @Override
            public void onFailure(Call<MessageThreadModel> call, Throwable t) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new RequestFailureEvent(true));
            }
        });
    }
}
