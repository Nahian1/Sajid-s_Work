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
import com.cryptenet.thanatos.dtmweb.home.transaction.mvp.TransactionFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.transaction.mvp.TransactionFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.transaction.mvp.TransactionFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.TransactionFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class TransactionFragmentModule {
    @Provides
    @PerFragment
    static TransactionFragmentContract.Presenter providePresenter(TransactionFragmentContract.Model model){
        return new TransactionFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static TransactionFragmentContract.Model provideModel(TransactionFragmentContract.Repository repository) {
        return new TransactionFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static TransactionFragmentContract.Repository provideRepository() {
        return new TransactionFragmentRepository();
    }
}
