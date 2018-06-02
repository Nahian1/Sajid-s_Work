/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.set_password;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.events.PwdResetEvent;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.SetPasswordActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPasswordActivity extends BaseActivity<SetPasswordActivityContract.Presenter>
        implements SetPasswordActivityContract.View {
    public static final String TAG = TagProvider.getDebugTag(SetPasswordActivity.class);

    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_confirm_new_pwd)
    EditText etConfirmNewPwd;
    @BindView(R.id.btn_done)
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        viewUnbinder = ButterKnife.bind(this);
    }

    @Override
    public SetPasswordActivity getActivity() {
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

    @OnClick(R.id.btn_done)
    public void onClick(View v) {

        ViewUtils.hideKeyboard(this);

        String pwd = etNewPwd.getText().toString().trim();
        String cPwd = etConfirmNewPwd.getText().toString().trim();

        if (!pwd.isEmpty() && !cPwd.isEmpty()) {
            if (pwd.equals(cPwd))
                presenter.sendPwdResetRequest(pwd, this);
            else
                showMessage("Filed values do not match");
        } else
            showMessage("Please fill both fields");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPwdResetEvent(PwdResetEvent event) {
        if (event.isSuccess) {
            showMessage("Reset Done!!");
            navigator.toLoginActivity(this);
            finish();
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
}
