/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.SetPasswordActivityModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;

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
