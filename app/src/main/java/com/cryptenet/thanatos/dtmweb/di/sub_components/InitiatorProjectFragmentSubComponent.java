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
