/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.RegistrationActivityModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerActivity
@Subcomponent(
        modules = RegistrationActivityModule.class
)
public interface RegistrationActivitySubComponent extends AndroidInjector<RegistrationActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RegistrationActivity> {
    }
}
