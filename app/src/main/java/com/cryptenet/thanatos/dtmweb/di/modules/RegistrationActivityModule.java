/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.registration.mvp.RegistrationActivityModel;
import com.cryptenet.thanatos.dtmweb.registration.mvp.RegistrationActivityPresenter;
import com.cryptenet.thanatos.dtmweb.registration.mvp.RegistrationActivityRepository;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseActivityModule.class
)
public abstract class RegistrationActivityModule {
    @Provides
    @PerActivity
    static RegistrationActivityContract.Presenter providePresenter(RegistrationActivityContract.Model model){
        return new RegistrationActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static RegistrationActivityContract.Model provideModel(RegistrationActivityContract.Repository repository) {
        return new RegistrationActivityModel(repository);
    }

    @Provides
    @PerActivity
    static RegistrationActivityContract.Repository provideRepository() {
        return new RegistrationActivityRepository();
    }
}
