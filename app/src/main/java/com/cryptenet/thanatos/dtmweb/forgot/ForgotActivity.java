package com.cryptenet.thanatos.dtmweb.forgot;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

public class ForgotActivity extends BaseActivity<ForgotActivityContract.Presenter>
        implements ForgotActivityContract.View {
    public static final String TAG = TagProvider.getDebugTag(ForgotActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public ForgotActivity getActivity() {
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
