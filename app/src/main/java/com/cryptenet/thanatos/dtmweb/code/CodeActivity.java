/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.code;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.CodeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

public class CodeActivity extends BaseActivity<CodeActivityContract.Presenter>
        implements CodeActivityContract.View {
    public static final String TAG = TagProvider.getDebugTag(CodeActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public CodeActivity getActivity() {
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
