/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.TransactionFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.transaction.TransactionFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(
        modules = TransactionFragmentModule.class
)
public interface TransactionFragmentSubComponent extends AndroidInjector<TransactionFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TransactionFragment> {
    }
}
