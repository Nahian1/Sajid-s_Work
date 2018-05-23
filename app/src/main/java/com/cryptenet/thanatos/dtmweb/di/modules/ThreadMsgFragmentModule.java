/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_msg.mvp.ThreadMsgFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.thread_msg.mvp.ThreadMsgFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.thread_msg.mvp.ThreadMsgFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadMsgFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class ThreadMsgFragmentModule {

    @Provides
    @PerFragment
    static ThreadMsgFragmentContract.Presenter providePresenter(ThreadMsgFragmentContract.Model model){
        return new ThreadMsgFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static ThreadMsgFragmentContract.Model provideModel(ThreadMsgFragmentContract.Repository repository) {
        return new ThreadMsgFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static ThreadMsgFragmentContract.Repository provideRepository() {
        return new ThreadMsgFragmentRepository();
    }
}
