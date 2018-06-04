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
