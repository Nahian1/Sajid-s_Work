/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

public class HomeActivity extends BaseActivity<HomeActivityContract.Presenter>
        implements HomeActivityContract.View {
    public static final String TAG = TagProvider.getDebugTag(HomeActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public HomeActivity getActivity() {
        return null;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void printLog(String TAG, String message) {
        Log.d(TAG, "printLog: " + message);

    }

    @Override
    public void restoreState(Bundle savedState) {

    }
}
