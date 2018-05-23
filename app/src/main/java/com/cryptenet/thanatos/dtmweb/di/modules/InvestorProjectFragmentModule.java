/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
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
