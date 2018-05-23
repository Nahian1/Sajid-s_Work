/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_desc.mvp.PlanDescFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.plan_desc.mvp.PlanDescFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.plan_desc.mvp.PlanDescFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanDescFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class PlanDescFragmentModule {
    @Provides
    @PerFragment
    static PlanDescFragmentContract.Presenter providePresenter(PlanDescFragmentContract.Model model){
        return new PlanDescFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static PlanDescFragmentContract.Model provideModel(PlanDescFragmentContract.Repository repository) {
        return new PlanDescFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static PlanDescFragmentContract.Repository provideRepository() {
        return new PlanDescFragmentRepository();
    }
}
