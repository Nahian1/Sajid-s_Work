/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.components;

import com.cryptenet.thanatos.dtmweb.di.modules.AppModule;
import com.cryptenet.thanatos.dtmweb.utils.CryptApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent extends AndroidInjector<CryptApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<CryptApplication> {}
}
