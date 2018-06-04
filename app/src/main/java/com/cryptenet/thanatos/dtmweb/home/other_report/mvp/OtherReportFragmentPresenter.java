/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.other_report.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.OtherReportFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class OtherReportFragmentPresenter extends BaseFragPresenter<OtherReportFragmentContract.View, OtherReportFragmentContract.Model>
        implements OtherReportFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(OtherReportFragmentPresenter.class);

    public OtherReportFragmentPresenter(OtherReportFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void sendIssue(Context context, int issueCode, String issue) {
        model.sendIssue(context, issueCode, issue);
    }
}
