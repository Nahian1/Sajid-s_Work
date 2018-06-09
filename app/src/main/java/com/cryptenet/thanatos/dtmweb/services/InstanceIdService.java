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

import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = TagProvider.getDebugTag(InstanceIdService.class);

    public InstanceIdService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "onTokenRefresh: " + token);
        sendToServer(token);
    }

    private void sendToServer(String token) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(ConstantProvider.FCM_TOKEN, token).apply();
    }
}
