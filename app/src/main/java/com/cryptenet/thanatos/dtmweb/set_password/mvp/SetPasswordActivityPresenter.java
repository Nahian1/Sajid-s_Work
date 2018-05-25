/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.set_password.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.SetPasswordActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class SetPasswordActivityPresenter extends BasePresenter<SetPasswordActivityContract.View, SetPasswordActivityContract.Model>
        implements SetPasswordActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(SetPasswordActivityPresenter.class);

    public SetPasswordActivityPresenter(SetPasswordActivityContract.Model model) {
        super(model);
    }

    @Override
    public void sendPwdResetRequest(String newPwd) {
        model.sendPwdResetRequest(newPwd);
    }
}
