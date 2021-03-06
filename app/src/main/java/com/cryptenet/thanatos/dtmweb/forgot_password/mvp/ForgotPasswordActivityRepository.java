/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.forgot_password.mvp;

import android.content.Context;
import android.preference.PreferenceManager;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.DataSendSuccessEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
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

@PerActivity
public class ForgotPasswordActivityRepository extends BaseRepository
        implements ForgotActivityContract.Repository {
//    private static String TAG = TagProvider.getDebugTag(ForgotPasswordActivityRepository.class);

    @Override
    public void sendIdentifier(String identifier) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("email", identifier)
                .build();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/account/forgot-password/")
                .post(formBody)
                .addHeader("Content-Type", head)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: forgot");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 200)
                        EventBus.getDefault().post(new DataSendSuccessEvent(true));
//                    else
//                        Log.d(TAG, "onResponse: " + response.code());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void saveIdentifierSP(String identifier, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(ConstantProvider.SP_FORGOT_PASSWORD_EMAIL, identifier)
                .apply();
    }
}
