/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.set_password.mvp;

import android.content.Context;

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
    public void sendPwdResetRequest(String newPwd, Context context) {
        model.sendPwdResetRequest(newPwd, context);
    }
}
