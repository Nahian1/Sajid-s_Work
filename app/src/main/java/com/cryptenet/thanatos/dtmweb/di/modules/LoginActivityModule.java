/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.launcher.mvp.LoginActivityModel;
import com.cryptenet.thanatos.dtmweb.launcher.mvp.LoginActivityPresenter;
import com.cryptenet.thanatos.dtmweb.launcher.mvp.LoginActivityRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseActivityModule.class
)
public abstract class LoginActivityModule {
    @Provides
    @PerActivity
    static LoginActivityContract.Presenter providePresenter(LoginActivityContract.Model model){
        return new LoginActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static LoginActivityContract.Model provideModel(LoginActivityContract.Repository repository) {
        return new LoginActivityModel(repository);
    }

    @Provides
    @PerActivity
    static LoginActivityContract.Repository provideRepository() {
        return new LoginActivityRepository();
    }
}
