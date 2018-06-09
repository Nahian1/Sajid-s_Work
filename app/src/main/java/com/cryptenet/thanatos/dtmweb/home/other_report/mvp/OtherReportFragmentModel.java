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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.OtherReportFragmentContract;

@PerFragment
public class OtherReportFragmentModel extends BaseFragModel<OtherReportFragmentContract.Repository>
        implements OtherReportFragmentContract.Model {
//    private static final String TAG = TagProvider.getDebugTag(OtherReportFragmentModel.class);

    public OtherReportFragmentModel(OtherReportFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void sendIssue(Context context, int issueCode, String issue) {
        repository.sendIssue(context, issueCode, issue);
    }
}
