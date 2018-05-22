/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.code.mvp.CodeActivityModel;
import com.cryptenet.thanatos.dtmweb.code.mvp.CodeActivityPresenter;
import com.cryptenet.thanatos.dtmweb.code.mvp.CodeActivityRepository;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.CodeActivityContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseActivityModule.class
)
public abstract class CodeActivityModule {
    @Provides
    @PerActivity
    static CodeActivityContract.Presenter providePresenter(CodeActivityContract.Model model){
        return new CodeActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static CodeActivityContract.Model provideModel(CodeActivityContract.Repository repository) {
        return new CodeActivityModel(repository);
    }

    @Provides
    @PerActivity
    static CodeActivityContract.Repository provideRepository() {
        return new CodeActivityRepository();
    }
}
