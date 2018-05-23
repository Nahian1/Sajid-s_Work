/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.mvp.InitiatorProjectFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.mvp.InitiatorProjectFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.mvp.InitiatorProjectFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InitiatorProjectFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class InitiatorProjectFragmentModule {
    @Provides
    @PerFragment
    static InitiatorProjectFragmentContract.Presenter providePresenter(InitiatorProjectFragmentContract.Model model){
        return new InitiatorProjectFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static InitiatorProjectFragmentContract.Model provideModel(InitiatorProjectFragmentContract.Repository repository) {
        return new InitiatorProjectFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static InitiatorProjectFragmentContract.Repository provideRepository() {
        return new InitiatorProjectFragmentRepository();
    }
}
