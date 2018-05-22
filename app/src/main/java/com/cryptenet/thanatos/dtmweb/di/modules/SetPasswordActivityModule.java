/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.SetPasswordActivityContract;
import com.cryptenet.thanatos.dtmweb.set_password.mvp.SetPasswordActivityModel;
import com.cryptenet.thanatos.dtmweb.set_password.mvp.SetPasswordActivityPresenter;
import com.cryptenet.thanatos.dtmweb.set_password.mvp.SetPasswordActivityRepository;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseActivityModule.class
)
public abstract class SetPasswordActivityModule {
    @Provides
    @PerActivity
    static SetPasswordActivityContract.Presenter providePresenter(SetPasswordActivityContract.Model model){
        return new SetPasswordActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static SetPasswordActivityContract.Model provideModel(SetPasswordActivityContract.Repository repository) {
        return new SetPasswordActivityModel(repository);
    }

    @Provides
    @PerActivity
    static SetPasswordActivityContract.Repository provideRepository() {
        return new SetPasswordActivityRepository();
    }
}
