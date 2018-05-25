/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.PlanLongDescFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_long_desc.PlanLongDescFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@PerFragment
@Subcomponent(
        modules = PlanLongDescFragmentModule.class
)
public interface PlanLongDescFragmentSubComponent extends AndroidInjector<PlanLongDescFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PlanLongDescFragment> {
    }
}
