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
import com.cryptenet.thanatos.dtmweb.home.thread_list.mvp.ThreadListFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.thread_list.mvp.ThreadListFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.thread_list.mvp.ThreadListFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class ThreadListFragmentModule {
    @Provides
    @PerFragment
    static ThreadListFragmentContract.Presenter providePresenter(ThreadListFragmentContract.Model model){
        return new ThreadListFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static ThreadListFragmentContract.Model provideModel(ThreadListFragmentContract.Repository repository) {
        return new ThreadListFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static ThreadListFragmentContract.Repository provideRepository() {
        return new ThreadListFragmentRepository();
    }
}

