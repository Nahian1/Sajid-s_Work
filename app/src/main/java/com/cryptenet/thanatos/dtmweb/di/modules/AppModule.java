/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import android.app.Activity;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;
import com.cryptenet.thanatos.dtmweb.di.sub_components.CodeActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.ForgotActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.HomeActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.LoginActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.RegistrationActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.SetPasswordActivitySubComponent;
import com.cryptenet.thanatos.dtmweb.forgot.ForgotActivity;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;
import com.cryptenet.thanatos.dtmweb.setpassword.SetPasswordActivity;
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
                LoginActivitySubComponent.class,
                RegistrationActivitySubComponent.class,
                ForgotActivitySubComponent.class,
                CodeActivitySubComponent.class,
                SetPasswordActivitySubComponent.class,
                HomeActivitySubComponent.class
        }
)
public abstract class AppModule {
    private static final String TAG = TagProvider.getDebugTag(AppModule.class);

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
    @ActivityKey(ForgotActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    ForgotActivityInjectorFactory(ForgotActivitySubComponent.Builder builder);

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
