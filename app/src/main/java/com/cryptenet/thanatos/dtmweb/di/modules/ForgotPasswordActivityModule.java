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
import com.cryptenet.thanatos.dtmweb.forgot_password.mvp.ForgotPasswordActivityModel;
import com.cryptenet.thanatos.dtmweb.forgot_password.mvp.ForgotPasswordActivityPresenter;
import com.cryptenet.thanatos.dtmweb.forgot_password.mvp.ForgotPasswordActivityRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseActivityModule.class
)
public abstract class ForgotPasswordActivityModule {
    @Provides
    @PerActivity
    static ForgotActivityContract.Presenter providePresenter(ForgotActivityContract.Model model){
        return new ForgotPasswordActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static ForgotActivityContract.Model provideModel(ForgotActivityContract.Repository repository) {
        return new ForgotPasswordActivityModel(repository);
    }

    @Provides
    @PerActivity
    static ForgotActivityContract.Repository provideRepository() {
        return new ForgotPasswordActivityRepository();
    }
}
