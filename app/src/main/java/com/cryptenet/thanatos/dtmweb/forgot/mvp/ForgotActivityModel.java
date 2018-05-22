/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.forgot.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.forgot.ForgotActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class ForgotActivityModel extends BaseModel<ForgotActivityContract.Repository, ForgotActivity>
        implements ForgotActivityContract.Model {
private static final String TAG = TagProvider.getDebugTag(ForgotActivityModel.class);

public ForgotActivityModel(ForgotActivityContract.Repository repository) {
        super(repository);
        }

@Override
public void attachContext(ForgotActivity context) {
        this.context = context;
        }
}
