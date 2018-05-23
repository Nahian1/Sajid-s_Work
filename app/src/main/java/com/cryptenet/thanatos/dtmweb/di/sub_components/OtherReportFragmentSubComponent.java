/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;


import com.cryptenet.thanatos.dtmweb.di.modules.OtherReportFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.other_report.OtherReportFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(
        modules = OtherReportFragmentModule.class
)
public interface OtherReportFragmentSubComponent extends AndroidInjector<OtherReportFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<OtherReportFragment> {
    }
}
