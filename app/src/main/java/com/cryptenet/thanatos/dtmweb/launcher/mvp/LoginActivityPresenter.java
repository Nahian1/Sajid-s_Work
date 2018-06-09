/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.launcher.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.User;

@PerActivity
public class LoginActivityPresenter
        extends BasePresenter<LoginActivityContract.View, LoginActivityContract.Model>
        implements LoginActivityContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(LoginActivityPresenter.class);

    public LoginActivityPresenter(LoginActivityContract.Model model) {
        super(model);
    }

    @Override
    public void requestForLogin(String email, String password) {
        model.requestForLogin(email, password);
    }

    @Override
    public boolean saveUserData(User user) {
        model.attachContext(view.getActivity());
        return model.saveUserData(user);
    }
}
