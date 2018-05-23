/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.request_detail.mvp.RequestDetailFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.request_detail.mvp.RequestDetailFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.request_detail.mvp.RequestDetailFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RequestDetailFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class RequestDetailFragmentModule {
    @Provides
    @PerFragment
    static RequestDetailFragmentContract.Presenter providePresenter(RequestDetailFragmentContract.Model model){
        return new RequestDetailFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static RequestDetailFragmentContract.Model provideModel(RequestDetailFragmentContract.Repository repository) {
        return new RequestDetailFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static RequestDetailFragmentContract.Repository provideRepository() {
        return new RequestDetailFragmentRepository();
    }
}
