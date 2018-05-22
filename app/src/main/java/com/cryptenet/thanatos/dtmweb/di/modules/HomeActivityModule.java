/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.di.sub_components.PlanListFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityModel;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityPresenter;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityRepository;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(
        includes = BaseFragActivityModule.class,
        subcomponents = {
                PlanListFragmentSubComponent.class
        }
)
public abstract class HomeActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity activity(HomeActivity activity);

    @Provides
    @PerActivity
    static HomeActivityContract.Presenter providePresenter(HomeActivityContract.Model model){
        return new HomeActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static HomeActivityContract.Model provideModel(HomeActivityContract.Repository repository) {
        return new HomeActivityModel(repository);
    }

    @Provides
    @PerActivity
    static HomeActivityContract.Repository provideRepository() {
        return new HomeActivityRepository();
    }

    @Binds
    @IntoMap
    @FragmentKey(PlanListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    PlanListFragmentInjectorFactory(PlanListFragmentSubComponent.Builder builder);
}
