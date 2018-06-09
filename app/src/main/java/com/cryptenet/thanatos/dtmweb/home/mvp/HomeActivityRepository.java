/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.mvp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.FCMRsp;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerActivity
public class HomeActivityRepository extends BaseRepository
        implements HomeActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(HomeActivityRepository.class);

    @Override
    public NavHeader getNavHeaderData(Context context) {
        return new NavHeader(
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_PICTURE_URL, null),
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_NAME, null),
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_USER_TYPE, null),
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ADDRESS, null)
        );
    }

    @Override
    public boolean clearUserData(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = sharedPreferences.getString(ConstantProvider.SELECTED_LANGUAGE, "en");
        return PreferenceManager.getDefaultSharedPreferences(context).edit().clear().putString(ConstantProvider.SELECTED_LANGUAGE, lang).commit();
    }

    @Override
    public void sendFCMData(Context context) {
        String token = PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.FCM_TOKEN, null);

        @SuppressLint("HardwareIds")
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        Call<FCMRsp> req = apiClient.sendFCMToken(
                "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                token,
                deviceId,
                "android",
                true
        );

        req.enqueue(new Callback<FCMRsp>() {
            @Override
            public void onResponse(Call<FCMRsp> call, Response<FCMRsp> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().toString());

                    PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(ConstantProvider.FCM_FLAG, false).apply();
                } else {
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FCMRsp> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
