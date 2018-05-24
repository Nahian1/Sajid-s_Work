/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.launcher.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class LoginActivityModel
        extends BaseModel<LoginActivityContract.Repository, LoginActivity>
        implements LoginActivityContract.Model {
    private static final String TAG = TagProvider.getDebugTag(LoginActivityModel.class);

    public LoginActivityModel(LoginActivityContract.Repository repository) {
        super(repository);
    }

    @Override
    public void attachContext(LoginActivity context) {
        this.context = context;
    }

    @Override
    public boolean requestForLogin(String email, String password, int type) {
        return repository.validateLogin(email, password, type);
    }
}
