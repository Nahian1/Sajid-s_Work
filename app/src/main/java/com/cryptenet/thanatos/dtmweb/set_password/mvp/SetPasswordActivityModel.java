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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.SetPasswordActivityContract;
import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;

@PerActivity
public class SetPasswordActivityModel extends BaseModel<SetPasswordActivityContract.Repository, SetPasswordActivity>
        implements SetPasswordActivityContract.Model {
//    private static final String TAG = TagProvider.getDebugTag(SetPasswordActivityModel.class);

    public SetPasswordActivityModel(SetPasswordActivityContract.Repository repository) {
        super(repository);
    }

    @Override
    public void attachContext(SetPasswordActivity context) {
        this.context = context;
    }

    @Override
    public void sendPwdResetRequest(String newPwd, Context context) {
        repository.sendPwdResetRequest(newPwd, context);
    }
}
