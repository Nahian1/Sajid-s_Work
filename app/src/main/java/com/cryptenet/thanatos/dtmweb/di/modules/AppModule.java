/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import android.app.Activity;

import com.cryptenet.thanatos.dtmweb.di.sub_components.LoginActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(
        includes = AndroidInjectionModule.class,
        subcomponents = {
                LoginActivitySubComponent.class
        }
)
public abstract class AppModule {
    private static final String TAG = TagProvider.getDebugTag(AppModule.class);

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    LoginActivityInjectorFactory(LoginActivitySubComponent.Builder builder);
}
