/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseContract;
import com.cryptenet.thanatos.dtmweb.utils.Navigator;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import javax.inject.Inject;

import butterknife.Unbinder;
import dagger.android.AndroidInjection;

public abstract class BaseActivity<P extends BaseContract.Presenter>
        extends AppCompatActivity {
    private static final String TAG = TagProvider.getDebugTag(BaseActivity.class);

    @Nullable
    protected Unbinder viewUnbinder;

    @Inject
    protected Navigator navigator;

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    //Activity state change
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter.restoreState(savedInstanceState);
    }
    //Activity state change

    //screen rotation
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    //screen rotation

    //lifecycle methods
    @Override
    protected void onDestroy() {
        if (presenter != null)
            presenter.detachView();

        if (viewUnbinder != null)
            viewUnbinder = null;

        super.onDestroy();
    }
}
