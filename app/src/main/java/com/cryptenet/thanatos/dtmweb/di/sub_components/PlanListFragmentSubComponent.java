/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.PlanListFragmentModule;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent (
        modules = PlanListFragmentModule.class
)
public interface PlanListFragmentSubComponent extends AndroidInjector<PlanListFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PlanListFragment> {
    }
}
