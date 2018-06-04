/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
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
