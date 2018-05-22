/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.utils;

import android.app.Activity;
import android.app.Application;

import com.cryptenet.thanatos.dtmweb.di.components.DaggerAppComponent;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class CryptApplication extends Application implements HasActivityInjector {
    private static final String TAG = TagProvider.getDebugTag(CryptApplication.class);

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().create(this).inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
