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
import com.cryptenet.thanatos.dtmweb.home.report_issue.mvp.ReportIssueFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.report_issue.mvp.ReportIssueFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.report_issue.mvp.ReportIssueFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ReportIssueFragmentContract;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = BaseFragmentModule.class
)
public abstract class ReportIssueFragmentModule {
    @Provides
    @PerFragment
    static ReportIssueFragmentContract.Presenter providePresenter(ReportIssueFragmentContract.Model model){
        return new ReportIssueFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static ReportIssueFragmentContract.Model provideModel(ReportIssueFragmentContract.Repository repository) {
        return new ReportIssueFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static ReportIssueFragmentContract.Repository provideRepository() {
        return new ReportIssueFragmentRepository();
    }
}
