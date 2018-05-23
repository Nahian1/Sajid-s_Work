/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.ForgotPasswordActivityModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerActivity
@Subcomponent(
        modules = ForgotPasswordActivityModule.class
)
public interface ForgotPasswordActivitySubComponent extends AndroidInjector<ForgotPasswordActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ForgotPasswordActivity> {
    }
}
