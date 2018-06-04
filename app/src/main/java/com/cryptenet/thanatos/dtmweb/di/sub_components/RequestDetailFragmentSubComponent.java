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

import com.cryptenet.thanatos.dtmweb.di.modules.RequestDetailFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.request_detail.RequestDetailFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent (
        modules = RequestDetailFragmentModule.class
)
public interface RequestDetailFragmentSubComponent extends AndroidInjector<RequestDetailFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RequestDetailFragment> {
    }
}
