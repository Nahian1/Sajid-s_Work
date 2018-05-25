/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.forgot_password.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class ForgotPasswordActivityPresenter extends BasePresenter<ForgotActivityContract.View, ForgotActivityContract.Model>
        implements ForgotActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(ForgotPasswordActivityPresenter.class);

    public ForgotPasswordActivityPresenter(ForgotActivityContract.Model model) {
        super(model);
    }

    @Override
    public void sendIdentifier(String identifier) {
        model.sendIdentifier(identifier);
    }

    @Override
    public void saveIdentifier(String identifier) {
        model.saveIdentifier(identifier);
    }
}
