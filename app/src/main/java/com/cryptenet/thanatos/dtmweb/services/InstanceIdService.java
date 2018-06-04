/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.services;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.events.DataSendSuccessEvent;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = TagProvider.getDebugTag(InstanceIdService.class);
    @Inject
    SharedPreferences preferences;

    public InstanceIdService(int id) {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        sendToServer(token);
    }

    @SuppressLint("HardwareIds")
    private void sendToServer(String token) {
        try {
            String head = "application/json";
            String oauth = preferences.getString(ConstantProvider.SP_ACCESS_TOKEN, null);

            if (oauth != null) {
                OkHttpClient client = new OkHttpClient();

                TelephonyManager telephonyManager;
                telephonyManager = (TelephonyManager) getSystemService(Context.
                        TELEPHONY_SERVICE);

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                String deviceId;
                String subscriberId = null;
                if (telephonyManager != null) {
                     deviceId = telephonyManager.getDeviceId();
                }
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                }
                RequestBody formBody = new FormBody.Builder()
                        .add("registration_id", token)
                        .add("device_id", subscriberId)
                        .add("type", "android")
                        .build();

                final Request request = new Request.Builder()
                        .url(ConstantProvider.BASE_URL + "api/v1/fcm/")
                        .post(formBody)
                        .addHeader("Content-Type", head)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: fcm");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            if (response.code() == 200)
                                EventBus.getDefault().post(new DataSendSuccessEvent(true));
                            else
                                Log.d(TAG, "onResponse: " + response.code());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
