/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.set_password.mvp;

import android.content.Context;
import android.preference.PreferenceManager;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.PwdResetEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.SetPasswordActivityContract;
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
public class SetPasswordActivityRepository extends BaseRepository
        implements SetPasswordActivityContract.Repository {
//    private static String TAG = TagProvider.getDebugTag(SetPasswordActivityRepository.class);

    @Override
    public void sendPwdResetRequest(String newPwd, Context context) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("email", PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_FORGOT_PASSWORD_EMAIL, null))
                .add("code", String.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getInt(ConstantProvider.SP_FORGOT_PASSWORD_CODE, 1)))
                .add("new_password", newPwd)
                .build();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/account/reset-password/")
                .post(formBody)
                .addHeader("Content-Type", head)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: reset");
                EventBus.getDefault().post(new PwdResetEvent(false));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.code() == 200)
                        EventBus.getDefault().post(new PwdResetEvent(true));
                    else
                        EventBus.getDefault().post(new PwdResetEvent(false));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
