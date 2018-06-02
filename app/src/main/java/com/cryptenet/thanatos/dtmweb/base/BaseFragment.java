/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseFragContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import javax.inject.Inject;

import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<P extends BaseFragContract.Presenter> extends Fragment {
    private static final String TAG = TagProvider.getDebugTag(BaseFragment.class);

    @Nullable
    protected Unbinder viewUnbinder;

    @Inject
    protected Context activityContext;

    @Inject
    protected P presenter;

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context);
    }
}
