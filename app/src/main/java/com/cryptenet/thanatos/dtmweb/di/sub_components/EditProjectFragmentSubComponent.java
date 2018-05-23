/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.sub_components;

import com.cryptenet.thanatos.dtmweb.di.modules.EditProjectFragmentModule;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.edit_project.EditProjectFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@PerFragment
@Subcomponent (
        modules = EditProjectFragmentModule.class
)
public interface EditProjectFragmentSubComponent extends AndroidInjector<EditProjectFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<EditProjectFragment> {
    }
}
