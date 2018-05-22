/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.set_password.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.SetPasswordActivityContract;
import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class SetPasswordActivityModel extends BaseModel<SetPasswordActivityContract.Repository, SetPasswordActivity>
        implements SetPasswordActivityContract.Model {
    private static final String TAG = TagProvider.getDebugTag(SetPasswordActivityModel.class);

    public SetPasswordActivityModel(SetPasswordActivityContract.Repository repository) {
        super(repository);
    }

    @Override
    public void attachContext(SetPasswordActivity context) {
        this.context = context;
    }
}
