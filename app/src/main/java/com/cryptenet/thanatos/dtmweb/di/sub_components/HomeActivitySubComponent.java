/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.HomeActivityModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerActivity
@Subcomponent(
        modules = HomeActivityModule.class
)
public interface HomeActivitySubComponent extends AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeActivity> {
    }
}
