/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.form.mvp.FormFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.form.mvp.FormFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.form.mvp.FormFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class FormFragmentModule {
    @Provides
    @PerFragment
    static FormFragmentContract.Presenter providePresenter(FormFragmentContract.Model model){
        return new FormFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static FormFragmentContract.Model provideModel(FormFragmentContract.Repository repository) {
        return new FormFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static FormFragmentContract.Repository provideRepository() {
        return new FormFragmentRepository();
    }
}
