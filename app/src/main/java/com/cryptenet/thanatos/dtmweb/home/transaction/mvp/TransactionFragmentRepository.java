/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.transaction.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.TransactionFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.TransactionDetails;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class TransactionFragmentRepository extends BaseFragRepository
        implements TransactionFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(TransactionFragmentRepository.class);

    @Override
    public void getTransactionDetails(Context context, int transactionId) {
        Call<TransactionDetails> call = apiClient.getTransactionDetails(
                "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null),
                transactionId
        );

        call.enqueue(new Callback<TransactionDetails>() {
            @Override
            public void onResponse(Call<TransactionDetails> call, Response<TransactionDetails> response) {
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<TransactionDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: " + "transaction details");
            }
        });
    }
}
