/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.request_detail.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.BackToManageRequestEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestDataReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RequestDetailFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.TransactionDetails;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class RequestDetailFragmentRepository extends BaseFragRepository
        implements RequestDetailFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(RequestDetailFragmentRepository.class);

    @Override
    public void getTransactionDetails(Context context, int transactionId, int userType) {
        if (userType == 2) {
            String head = "application/json";
            Call<TransactionDetails> call = apiClient.getTransactionDetails(
                    "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null),
                    transactionId
            );

            call.enqueue(new Callback<TransactionDetails>() {
                @Override
                public void onResponse(Call<TransactionDetails> call, Response<TransactionDetails> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    TransactionDetails details = response.body();

//                    OkHttpClient client = new OkHttpClient();
//
//                    Request request = new Request.Builder()
//                            .url(ConstantProvider.BASE_URL + "api/v1/user/" + response.body().getInvestor() + "/")
//                            .get()
//                            .addHeader("Content-Type", head)
//                            .addHeader("Authorization", "Bearer " + PreferenceManager
//                                    .getDefaultSharedPreferences(context)
//                                    .getString(ConstantProvider.SP_ACCESS_TOKEN, null))
//                            .build();
//
//                    client.newCall(request).enqueue(new okhttp3.Callback() {
//                        @Override
//                        public void onFailure(okhttp3.Call call, IOException e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                            Log.d(TAG, response.body().string());
//                            Gson gson = new Gson();
//                            EventBus.getDefault().post(new RequestDataReceiveEvent(
//                                    details,
//                                    gson.fromJson(response.body().string(), User.class))
//                            );
//                        }
//                    });

                    Call<User> userCall = apiClient.getUserData(
                            "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null),
                            details.getInvestor()
                    );

                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.d(TAG, response.body().toString());
                            EventBus.getDefault().post(new RequestDataReceiveEvent(details, response.body()));
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<TransactionDetails> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + "transaction details");
                }
            });
        } else {
            Call<TransactionDetails> call = apiClient.getTransactionDetails(
                    "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null),
                    transactionId
            );

            call.enqueue(new Callback<TransactionDetails>() {
                @Override
                public void onResponse(Call<TransactionDetails> call, Response<TransactionDetails> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    TransactionDetails details = response.body();

                    EventBus.getDefault().post(new RequestDataReceiveEvent(details, null));
                }

                @Override
                public void onFailure(Call<TransactionDetails> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + "transaction details");
                }
            });
        }

    }

    @Override
    public void confirmRequest(Context context, int transactionId) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"is_approved\"\r\n\r\ntrue\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("http://fa-sa-801-dev.herokuapp.com/api/v1/plan-access/" + transactionId + "/access/")
                .put(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null))
                .addHeader("Cache-Control", "no-cache")
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d(TAG, "onFailure:");
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                assert response.body() != null;
                Log.d(TAG, "onResponse: " + response.body().string());

                if (response.code() == 200) {
                    EventBus.getDefault().post(new BackToManageRequestEvent());
                }
            }
        });
    }
}
