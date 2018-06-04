/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
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
