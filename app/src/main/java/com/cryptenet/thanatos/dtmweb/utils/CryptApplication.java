/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.cryptenet.thanatos.dtmweb.BuildConfig;

import com.cryptenet.thanatos.dtmweb.R;

import com.cryptenet.thanatos.dtmweb.di.components.DaggerAppComponent;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.util.UUID;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

@ReportsCrashes(
        mailTo = "asif.rahman307@gmail.com, cryptenet@gmail.com",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.toast_crash,
        customReportContent = {
                ReportField.BRAND,
                ReportField.PHONE_MODEL,
                ReportField.ANDROID_VERSION,
                ReportField.STACK_TRACE
        }
)
public class CryptApplication extends Application implements HasActivityInjector {
    private static final String TAG = TagProvider.getDebugTag(CryptApplication.class);

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.setLocale(base));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LocaleHelper.setLocale(this);
        id(this);

        if (BuildConfig.DEBUG) {
            // The following line triggers the initialization of ACRA
            ACRA.init(this);

            Fabric.with(this, new Crashlytics(), new Answers());
        }

        DaggerAppComponent.builder().create(this).inject(this);

        //configure timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.apply();
            }
        }
        return uniqueID;
    }
}
