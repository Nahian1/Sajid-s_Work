/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.form.mvp;

import android.content.Context;
import android.preference.PreferenceManager;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.TransactionSuccessEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
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
//    private static String TAG = TagProvider.getDebugTag(FormFragmentRepository.class);

    @Override
    public void submitTransactionData(Transaction transaction, Context context) {

        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("plan", String.valueOf(transaction.getProjectsDetailed().getId()))
                .add("bank_name", transaction.getBankName())
                .add("bank_account_name", transaction.getBankAccountName())
                .add("bank_account_number", transaction.getBankAccountNumber())
                .add("transaction_id", transaction.getTransactionId())
                .add("note", transaction.getNote())
                .build();

//        Log.d(TAG, "data: " + formBody.toString());

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/plan-access/")
                .post(formBody)
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure:");
                e.printStackTrace();

                EventBus.getDefault().post(new RequestFailureEvent(true));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Transaction transaction1 = new Gson().fromJson(response.body().string(), Transaction.class);
                transaction1.setProjectsDetailed(transaction.getProjectsDetailed());

                if (response.isSuccessful()) {

                    EventBus.getDefault().post(new TransactionSuccessEvent(transaction1));

                } else {

                    EventBus.getDefault().post(new RequestFailureEvent(true));
                }

            }
        });
    }
}
