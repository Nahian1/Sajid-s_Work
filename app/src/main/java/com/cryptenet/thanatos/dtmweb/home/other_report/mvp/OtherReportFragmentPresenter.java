/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.other_report.mvp;

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
}
