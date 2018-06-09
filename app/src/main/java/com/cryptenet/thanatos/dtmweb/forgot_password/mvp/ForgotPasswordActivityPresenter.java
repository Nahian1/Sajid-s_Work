/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.forgot_password.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;

@PerActivity
public class ForgotPasswordActivityPresenter extends BasePresenter<ForgotActivityContract.View, ForgotActivityContract.Model>
        implements ForgotActivityContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(ForgotPasswordActivityPresenter.class);

    public ForgotPasswordActivityPresenter(ForgotActivityContract.Model model) {
        super(model);
    }

    @Override
    public void sendIdentifier(String identifier) {
        model.sendIdentifier(identifier);
    }

    @Override
    public void saveIdentifier(String identifier, Context context) {
        model.saveIdentifier(identifier, context);
    }
}
