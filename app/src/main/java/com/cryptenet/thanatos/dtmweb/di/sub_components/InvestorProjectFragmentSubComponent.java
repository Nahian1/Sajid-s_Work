/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.InvestorProjectFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.investor_project.InvestorProjectFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent (
        modules = InvestorProjectFragmentModule.class
)
public interface InvestorProjectFragmentSubComponent extends AndroidInjector<InvestorProjectFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<InvestorProjectFragment> {
    }
}
