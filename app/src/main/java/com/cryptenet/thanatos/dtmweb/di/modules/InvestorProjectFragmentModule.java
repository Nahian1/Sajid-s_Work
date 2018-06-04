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
import com.cryptenet.thanatos.dtmweb.home.investor_project.mvp.InvestorProjectFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.investor_project.mvp.InvestorProjectFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.investor_project.mvp.InvestorProjectFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InvestorProjectFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class InvestorProjectFragmentModule {
    @Provides
    @PerFragment
    static InvestorProjectFragmentContract.Presenter providePresenter(InvestorProjectFragmentContract.Model model){
        return new InvestorProjectFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static InvestorProjectFragmentContract.Model provideModel(InvestorProjectFragmentContract.Repository repository) {
        return new InvestorProjectFragmentModel(repository);
    }
    @Provides
    @PerFragment
    static InvestorProjectFragmentContract.Repository provideRepository() {
        return new InvestorProjectFragmentRepository();
    }
}
