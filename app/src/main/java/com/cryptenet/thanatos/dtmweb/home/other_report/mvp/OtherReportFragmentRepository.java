/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.other_report.mvp;

import android.content.Context;
import android.preference.PreferenceManager;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.IssueSubmittedEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.OtherReportFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

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
public class OtherReportFragmentRepository extends BaseFragRepository
        implements OtherReportFragmentContract.Repository {
//    private static String TAG = TagProvider.getDebugTag(OtherReportFragmentRepository.class);

    @Override
    public void sendIssue(Context context, int issueCode, String issue) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("topic", String.valueOf(issueCode))
                .add("details", issue)
                .build();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/issue/")
                .post(formBody)
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider
                        .SP_ACCESS_TOKEN, null))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: reset");

                EventBus.getDefault().post(new RequestFailureEvent(true));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 201)
                        EventBus.getDefault().post(new IssueSubmittedEvent(true));
                    else
                        EventBus.getDefault().post(new RequestFailureEvent(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
