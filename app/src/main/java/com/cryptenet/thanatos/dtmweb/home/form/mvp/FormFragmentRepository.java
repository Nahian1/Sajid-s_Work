/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.form.mvp;

import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.LogInSuccessEvent;
import com.cryptenet.thanatos.dtmweb.events.TransactionSuccessEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
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
public class FormFragmentRepository extends BaseFragRepository
        implements FormFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(FormFragmentRepository.class);

    @Override
    public void submitTransactionData(Transaction transaction) {

        EventBus.getDefault().post(new TransactionSuccessEvent(transaction)); //for test purpose

        String head = "application/x-www-form-urlencoded";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("bankName", "")
                .add("bankAccName", "")
                .add("bankAccNo", "")
                .add("transId", "")
                .add("note", "")
                .build();

        Log.d(TAG, "data: " + formBody.toString());

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "/api/v1/plan-access/")
                .post(formBody)
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Basic " + ConstantProvider.ACCESS_BASIC)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure:");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                if (response.isSuccessful())
//                    EventBus.getDefault()
//                            .post(new TransactionSuccessEvent(new Gson().
//                                    fromJson(response.body().toString(), Transaction.class)));

            }

        });
    }
}
