/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanLongDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.home.plan_long_desc.mvp.PlanLongDescFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.plan_long_desc.mvp.PlanLongDescFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.plan_long_desc.mvp.PlanLongDescFragmentRepository;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class PlanLongDescFragmentModule {
    @Provides
    @PerFragment
    static PlanLongDescFragmentContract.Presenter providePresenter(PlanLongDescFragmentContract.Model model){
        return new PlanLongDescFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static PlanLongDescFragmentContract.Model provideModel(PlanLongDescFragmentContract.Repository repository) {
        return new PlanLongDescFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static PlanLongDescFragmentContract.Repository provideRepository() {
        return new PlanLongDescFragmentRepository();
    }
}
