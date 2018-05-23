/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.PlanDescFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_desc.PlanDescFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(
        modules = PlanDescFragmentModule.class
)
public interface PlanDescFragmentSubComponent extends AndroidInjector<PlanDescFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PlanDescFragment> {
    }
}
