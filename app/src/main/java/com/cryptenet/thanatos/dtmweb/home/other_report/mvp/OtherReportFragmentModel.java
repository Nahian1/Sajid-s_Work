/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.other_report.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.OtherReportFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class OtherReportFragmentModel extends BaseFragModel<OtherReportFragmentContract.Repository>
        implements OtherReportFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(OtherReportFragmentModel.class);

    public OtherReportFragmentModel(OtherReportFragmentContract.Repository repository) {
        super(repository);
    }
}
