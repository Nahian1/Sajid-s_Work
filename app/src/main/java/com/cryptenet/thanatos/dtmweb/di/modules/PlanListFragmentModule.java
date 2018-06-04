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
import com.cryptenet.thanatos.dtmweb.home.plan_list.mvp.PlanListFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.plan_list.mvp.PlanListFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.plan_list.mvp.PlanListFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module (
        includes = BaseFragmentModule.class
)
public abstract class PlanListFragmentModule {
    @Provides
    @PerFragment
    static PlanListFragmentContract.Presenter providePresenter(PlanListFragmentContract.Model model){
        return new PlanListFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static PlanListFragmentContract.Model provideModel(PlanListFragmentContract.Repository repository) {
        return new PlanListFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static PlanListFragmentContract.Repository provideRepository() {
        return new PlanListFragmentRepository();
    }
}
