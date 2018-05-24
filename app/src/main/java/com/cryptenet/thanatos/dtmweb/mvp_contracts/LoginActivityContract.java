/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;

public interface LoginActivityContract {
    interface Presenter extends BaseContract.Presenter<LoginActivityContract.View> {
        void requestForLogin(String email, String password, int type);
    }

    interface View extends BaseContract.View<LoginActivity> {
    }

    interface Model extends BaseContract.Model<LoginActivity> {
        boolean requestForLogin(String email, String password, int type);
    }

    interface Repository extends BaseContract.Repository {
        boolean validateLogin(String email, String password, int type);
    }
}
