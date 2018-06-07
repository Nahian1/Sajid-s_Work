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

import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.events.FCMTokenEvent;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.greenrobot.eventbus.EventBus;

public class InstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = TagProvider.getDebugTag(InstanceIdService.class);

    public InstanceIdService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh: " + token);
        sendToServer(token);
    }

    private void sendToServer(String token) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("TOKEN", token).apply();
    }


//    private void sendToServer(String token) {
//        try {
//            String head = "application/json";
//            String oauth = PreferenceManager.getDefaultSharedPreferences(this)
//                    .getString(ConstantProvider.SP_ACCESS_TOKEN, null);
//
//            if (oauth != null) {
//                OkHttpClient client = new OkHttpClient();
//
//                String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
//                RequestBody formBody = new FormBody.Builder()
//                        .add("registration_id", token)
//                        .add("device_id", deviceId)
//                        .add("type", "android")
//                        .add("active", "true")
//                        .build();
//
//                final Request request = new Request.Builder()
//                        .url(ConstantProvider.BASE_URL + "api/v1/fcm/")
//                        .post(formBody)
//                        .addHeader("Content-Type", head)
//                        .addHeader("Authorization", oauth)
//                        .build();
//
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.d(TAG, "onFailure: fcm");
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        try {
//                            if (response.isSuccessful())
//                                Log.d(TAG, response.body().string());
//                            else
//                                Log.d(TAG, "onResponse: " + response.code());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
