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
import com.cryptenet.thanatos.dtmweb.home.thread_project.mvp.ThreadProjectFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.thread_project.mvp.ThreadProjectFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.thread_project.mvp.ThreadProjectFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class ThreadProjectFragmentModule {
    @Provides
    @PerFragment
    static ThreadProjectFragmentContract.Presenter providePresenter(ThreadProjectFragmentContract.Model model){
        return new ThreadProjectFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static ThreadProjectFragmentContract.Model provideModel(ThreadProjectFragmentContract.Repository repository) {
        return new ThreadProjectFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static ThreadProjectFragmentContract.Repository provideRepository() {
        return new ThreadProjectFragmentRepository();
    }
}
