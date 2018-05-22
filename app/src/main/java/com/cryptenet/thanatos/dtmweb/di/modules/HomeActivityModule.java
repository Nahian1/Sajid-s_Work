/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityModel;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityPresenter;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseActivityModule.class
)
public abstract class HomeActivityModule {
    @Provides
    @PerActivity
    static HomeActivityContract.Presenter providePresenter(HomeActivityContract.Model model){
        return new HomeActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static HomeActivityContract.Model provideModel(HomeActivityContract.Repository repository) {
        return new HomeActivityModel(repository);
    }

    @Provides
    @PerActivity
    static HomeActivityContract.Repository provideRepository() {
        return new HomeActivityRepository();
    }
}
