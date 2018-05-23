/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;
import com.cryptenet.thanatos.dtmweb.di.sub_components.CodeActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.ForgotPasswordActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.HomeActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.LoginActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.RegistrationActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.SetPasswordActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;
import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;
import com.cryptenet.thanatos.dtmweb.utils.CryptApplication;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(
        includes = AndroidInjectionModule.class,
        subcomponents = {
                LoginActivitySubComponent.class,
                RegistrationActivitySubComponent.class,
                ForgotPasswordActivitySubComponent.class,
                CodeActivitySubComponent.class,
                SetPasswordActivitySubComponent.class,
                HomeActivitySubComponent.class
        }
)
public abstract class AppModule {
    private static final String TAG = TagProvider.getDebugTag(AppModule.class);

    @Provides
    @Singleton
    static SharedPreferences providesSharedPreferences(CryptApplication context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    LoginActivityInjectorFactory(LoginActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(RegistrationActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    RegistrationActivityInjectorFactory(RegistrationActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(ForgotPasswordActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    ForgotActivityInjectorFactory(ForgotPasswordActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(CodeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    CodeActivityInjectorFactory(CodeActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SetPasswordActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    SetPasswordActivityInjectorFactory(SetPasswordActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    HomeActivityInjectorFactory(HomeActivitySubComponent.Builder builder);
}
