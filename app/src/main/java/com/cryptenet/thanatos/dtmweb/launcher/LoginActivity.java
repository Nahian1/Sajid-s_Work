/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.launcher;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.events.LogInSuccessEvent;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.LocaleHelper;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.services.concurrency.AsyncTask;


public class LoginActivity extends BaseActivity<LoginActivityContract.Presenter>
        implements LoginActivityContract.View, View.OnClickListener {
    private static final String TAG = TagProvider.getDebugTag(LoginActivity.class);
    private User user;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_pwd)
    EditText etPwd;

    @BindView(R.id.btn_sign_in)
    Button btnSignIn;

    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;

    @BindView(R.id.tv_forgot_pwd)
    TextView tvForgotPwd;

    ProgressDialog progressDialog;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (preferences.getString(ConstantProvider.SP_NAME, null) != null) {
            navigator.toHomeActivity(this, null);
            finish();
        }

        String lang = preferences.getString(ConstantProvider.SELECTED_LANGUAGE, null);

        if (lang == null) {
            LocaleHelper.setNewLocale(this, "en");
//            preferences.edit().putString(ConstantProvider.LOCALE, "en").apply();
        } else {
            LocaleHelper.setNewLocale(this, lang);
        }

        viewUnbinder = ButterKnife.bind(this);

        btnSignIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        tvForgotPwd.setOnClickListener(this);
    }

    @Override
    public LoginActivity getActivity() {
        return this;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:

                ViewUtils.hideKeyboard(this);

                //for debug only
//                String email = etEmail.getText().toString().trim();
//
//                if (email.equals("1")) {
//
//                    ProgressDialogHelper.init(this).showProgress();
//
//                    presenter.requestForLogin("michaelperez@collier.com", "asdasd123");
//
//                } else if (email.equals("2")) {
//
//                    ProgressDialogHelper.init(this).showProgress();
//
//                    presenter.requestForLogin("creynolds@montgomery.com", "asdasd123");
//
//                } else {
//                    showMessage("Give a type!");
//                }

//                presenter.requestForLogin("azam@gmail.com","asdasd123");

                //original code commented out for debug purpose
                String email = etEmail.getText().toString().trim();
                String password = etPwd.getText().toString().trim();

                if (!email.isEmpty()) {
                    if (!password.isEmpty()) {

                        ProgressDialogHelper.init(this).showProgress();

                        presenter.requestForLogin(
                                etEmail.getText().toString().trim(),
                                etPwd.getText().toString().trim()
                        );
                    } else {
                        showMessage("Password can not be empty!");
                    }
                } else {
                    showMessage("Email can not be empty!");
                }

                break;
            case R.id.tv_sign_up:
                navigator.toRegistrationActivity(this, false);
                break;
            case R.id.tv_forgot_pwd:
                navigator.toForgotPasswordActivity(this);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogInSuccessEvent(LogInSuccessEvent event) {
//        this.user = event.string;

        ProgressDialogHelper.hideProgress();

        if (event.isSuccess) {
            AsyncTask.execute(() -> {
//                    showMessage("Loading data...");
                try {
                    if (presenter.saveUserData(new Gson().fromJson(event.string, User.class))) {
                        navigator.toHomeActivity(LoginActivity.this, event.string);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = PreferenceManager.getDefaultSharedPreferences(newBase).getString(ConstantProvider.SELECTED_LANGUAGE, "en");
        super.attachBaseContext(LocaleHelper.setNewLocale(newBase, lang));
    }

}
