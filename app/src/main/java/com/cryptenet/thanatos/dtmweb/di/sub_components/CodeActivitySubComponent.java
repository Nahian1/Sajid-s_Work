/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;
import com.cryptenet.thanatos.dtmweb.di.modules.CodeActivityModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerActivity
@Subcomponent(
        modules = CodeActivityModule.class
)
public interface CodeActivitySubComponent extends AndroidInjector<CodeActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CodeActivity> {
    }
}
