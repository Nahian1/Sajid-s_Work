/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.report_issue.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ReportIssueFragmentContract;

@PerFragment
public class ReportIssueFragmentModel extends BaseFragModel<ReportIssueFragmentContract.Repository>
        implements ReportIssueFragmentContract.Model {
//    private static final String TAG = TagProvider.getDebugTag(ReportIssueFragmentModel.class);

    public ReportIssueFragmentModel(ReportIssueFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getAllIssues(Context context) {
        repository.getAllIssues(context);
    }
}
