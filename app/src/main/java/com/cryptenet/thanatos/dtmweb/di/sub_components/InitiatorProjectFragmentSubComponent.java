/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.InitiatorProjectFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.InitiatorProjectFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(
        modules = InitiatorProjectFragmentModule.class
)
public interface InitiatorProjectFragmentSubComponent extends AndroidInjector<InitiatorProjectFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<InitiatorProjectFragment> {
    }
}
