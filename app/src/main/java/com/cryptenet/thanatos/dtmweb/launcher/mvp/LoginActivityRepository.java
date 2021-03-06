/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.launcher.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.LogInFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.LogInSuccessEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.User;
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
public class LoginActivityRepository extends BaseRepository
        implements LoginActivityContract.Repository {
//    private static String TAG = TagProvider.getDebugTag(LoginActivityRepository.class);

    public LoginActivityRepository() {

    }

    @Override
    public void validateLogin(String email, String password) {
        String head = "application/x-www-form-urlencoded";

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", email)
                .add("password", password)
                .build();
//        Log.d(TAG, "validateLogin: " + formBody.toString());
        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "o/token/")
                .post(formBody)
                .addHeader("Content-Type", head)
                .addHeader("Authorization", "Basic " + ConstantProvider.ACCESS_BASIC)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: login");
                EventBus.getDefault().post(new LogInFailureEvent(true));
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    EventBus.getDefault().post(new LogInSuccessEvent(response.body().string(), response.body() != null));
                }else {
                    Log.d("login failed",response.body().toString());
                    EventBus.getDefault().post(new LogInFailureEvent(true));
                }

            }


        });
    }

    @Override
    public boolean saveUserToSP(User user, Context context) {
        long expiresIn = System.currentTimeMillis() + (user.getExpiresIn() * 1000);

        return PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(ConstantProvider.SP_ACCESS_TOKEN, user.getAccessToken())
                .putString(ConstantProvider.SP_REFRESH_TOKEN, user.getRefreshToken())
                .putInt(ConstantProvider.SP_ID, user.getId())
                .putString(ConstantProvider.SP_NAME, user.getName())
                .putString(ConstantProvider.SP_EMAIL, user.getEmail())
                .putString(ConstantProvider.SP_PICTURE_URL, user.getPicture())
                .putString(ConstantProvider.SP_ADDRESS, user.getAddress())
                .putInt(ConstantProvider.SP_CITY, user.getCity())
                .putInt(ConstantProvider.SP_COUNTRY, user.getCountry())
                .putString(ConstantProvider.SP_BANK_NAME, user.getBankName())
                .putString(ConstantProvider.SP_BANK_ACC_NAME, user.getBankAccountName())
                .putString(ConstantProvider.SP_BANK_ACC_NO, user.getBankAccountNumber())
                .putString(ConstantProvider.SP_USER_TYPE, user.getUserType())
                .putLong(ConstantProvider.SP_EXPIRES_IN, expiresIn)
                .commit();
    }
}
