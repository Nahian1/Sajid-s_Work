/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseFragActivity<P extends BaseContract.Presenter> extends BaseActivity<P>
        implements HasSupportFragmentInjector {
    private static final String TAG = TagProvider.getDebugTag(BaseFragActivity.class);

    @Inject
    @Named(ConstantProvider.ACTIVITY_FRAGMENT_MANAGER)
    FragmentManager fragmentManager;

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    protected final void addFragment(@IdRes int containerViewId, Fragment fragment) {
        fragmentManager
                .beginTransaction()
//                .addToBackStack(null) //commented out by Asif
                .add(containerViewId, fragment)
                .commit();
    }

    protected final void replaceFragment(@IdRes int containerViewId, Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(containerViewId, fragment)
                .commit();
    }
}
