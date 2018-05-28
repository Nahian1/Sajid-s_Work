/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.report_issue.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ReportIssueFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ReportIssueFragmentModel extends BaseFragModel<ReportIssueFragmentContract.Repository>
        implements ReportIssueFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(ReportIssueFragmentModel.class);

    public ReportIssueFragmentModel(ReportIssueFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getAllIssues() {
        repository.getAllIssues();
    }
}
