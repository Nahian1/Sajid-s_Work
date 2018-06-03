package com.cryptenet.thanatos.dtmweb.forgot_password;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.events.DataSendSuccessEvent;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.LocaleHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends BaseActivity<ForgotActivityContract.Presenter>
        implements ForgotActivityContract.View, View.OnClickListener {
    public static final String TAG = TagProvider.getDebugTag(ForgotPasswordActivity.class);

    @BindView(R.id.et_forgot)
    EditText etForgot;

    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        viewUnbinder = ButterKnife.bind(this);

        btnNext.setOnClickListener(this);
    }

    @Override
    public ForgotPasswordActivity getActivity() {
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

        ViewUtils.hideKeyboard(this);

        String mail = etForgot.getText().toString().trim();
        if (!mail.isEmpty()) {
            presenter.saveIdentifier(mail, this);
            presenter.sendIdentifier(mail);

            navigator.toCodeActivity(this);
        } else
            showMessage("Field can not be empty");
    }

    @Subscribe
    public void onDataSendSuccessEvent(DataSendSuccessEvent event) {
        if (event.isSuccess)
            navigator.toCodeActivity(this);
        else
            showMessage("Invalid Email!");
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
