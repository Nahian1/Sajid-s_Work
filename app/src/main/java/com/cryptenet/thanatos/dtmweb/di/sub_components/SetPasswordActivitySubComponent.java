/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.SetPasswordActivityModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.setpassword.SetPasswordActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerActivity
@Subcomponent(
        modules = SetPasswordActivityModule.class
)
public interface SetPasswordActivitySubComponent extends AndroidInjector<SetPasswordActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SetPasswordActivity> {
    }
}
