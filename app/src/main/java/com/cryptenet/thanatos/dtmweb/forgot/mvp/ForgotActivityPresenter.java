/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.forgot.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class ForgotActivityPresenter extends BasePresenter<ForgotActivityContract.View, ForgotActivityContract.Model>
        implements ForgotActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(ForgotActivityPresenter.class);

    public ForgotActivityPresenter(ForgotActivityContract.Model model) {
        super(model);
    }
}
