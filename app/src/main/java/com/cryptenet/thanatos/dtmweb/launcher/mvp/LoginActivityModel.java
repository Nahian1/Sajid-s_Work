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
import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.LoginActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.User;

@PerActivity
public class LoginActivityModel
        extends BaseModel<LoginActivityContract.Repository, LoginActivity>
        implements LoginActivityContract.Model {
//    private static final String TAG = TagProvider.getDebugTag(LoginActivityModel.class);

    public LoginActivityModel(LoginActivityContract.Repository repository) {
        super(repository);
    }

    @Override
    public void attachContext(LoginActivity context) {
        this.context = context;
    }

    @Override
    public void requestForLogin(String email, String password) {
        repository.validateLogin(email, password);
    }

    @Override
    public boolean saveUserData(User user) {
        return repository.saveUserToSP(user, context);
    }
}
