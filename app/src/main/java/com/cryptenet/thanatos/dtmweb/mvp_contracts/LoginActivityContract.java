/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.pojo.User;

public interface LoginActivityContract {
    interface Presenter extends BaseContract.Presenter<LoginActivityContract.View> {
        void requestForLogin(String email, String password);
        boolean saveUserData(User user);
    }

    interface View extends BaseContract.View<LoginActivity> {
    }

    interface Model extends BaseContract.Model<LoginActivity> {
        void requestForLogin(String email, String password);
        boolean saveUserData(User user);
    }

    interface Repository extends BaseContract.Repository {
        void validateLogin(String email, String password);
        boolean saveUserToSP(User user, Context context);
    }
}
