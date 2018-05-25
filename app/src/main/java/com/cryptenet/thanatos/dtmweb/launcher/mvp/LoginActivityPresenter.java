/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.launcher.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class LoginActivityPresenter
        extends BasePresenter<LoginActivityContract.View, LoginActivityContract.Model>
        implements LoginActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(LoginActivityPresenter.class);

    public LoginActivityPresenter(LoginActivityContract.Model model) {
        super(model);
    }

    @Override
    public void requestForLogin(String email, String password) {
        model.requestForLogin(email, password);
    }

    @Override
    public void saveUserData(User user) {
        model.saveUserData(user);
    }
}
