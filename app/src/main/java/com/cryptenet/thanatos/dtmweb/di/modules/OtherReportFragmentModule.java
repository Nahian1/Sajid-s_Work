/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.other_report.mvp.OtherReportFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.other_report.mvp.OtherReportFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.other_report.mvp.OtherReportFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.OtherReportFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class OtherReportFragmentModule {
    @Provides
    @PerFragment
    static OtherReportFragmentContract.Presenter providePresenter(OtherReportFragmentContract.Model model){
        return new OtherReportFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static OtherReportFragmentContract.Model provideModel(OtherReportFragmentContract.Repository repository) {
        return new OtherReportFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static OtherReportFragmentContract.Repository provideRepository() {
        return new OtherReportFragmentRepository();
    }
}
