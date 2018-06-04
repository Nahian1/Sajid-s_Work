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
