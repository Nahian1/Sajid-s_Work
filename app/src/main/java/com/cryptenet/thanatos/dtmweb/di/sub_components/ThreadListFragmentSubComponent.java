/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.ThreadListFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_list.ThreadListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(
        modules = ThreadListFragmentModule.class
)
public interface ThreadListFragmentSubComponent extends AndroidInjector<ThreadListFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ThreadListFragment> {
    }
}
