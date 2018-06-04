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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ReportIssueFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ReportIssueFragmentPresenter extends BaseFragPresenter<ReportIssueFragmentContract.View, ReportIssueFragmentContract.Model>
        implements ReportIssueFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(ReportIssueFragmentPresenter.class);

    public ReportIssueFragmentPresenter(ReportIssueFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void getAllIssues(Context context) {
        model.getAllIssues(context);
    }

}
